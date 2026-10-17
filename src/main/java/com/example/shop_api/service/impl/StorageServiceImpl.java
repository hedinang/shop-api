package com.example.shop_api.service.impl;

import com.example.shop_api.model.dto.CompleteMultipartDto;
import com.example.shop_api.model.dto.MultipartDto;
import com.example.shop_api.model.dto.PartUrlDto;
import com.example.shop_api.model.request.CompleteMultipartRequest;
import com.example.shop_api.model.request.CompletedPartRequest;
import com.example.shop_api.model.request.MultipartRequest;
import com.example.shop_api.model.request.PartUrlRequest;
import com.example.shop_api.service.StorageService;
import com.example.shop_api.util.date.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedUploadPartRequest;
import software.amazon.awssdk.services.s3.presigner.model.UploadPartPresignRequest;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private static final long PART_SIZE =
            10L * 1024L * 1024L;

    private static final long MAX_FILE_SIZE =
            1024L * 1024L * 1024L; // 1 GB

    private static final int MAX_PARTS = 10_000;

    @Value("${storage.minio.internal-endpoint}")
    String internalEndpoint;
    @Value("${storage.minio.public-endpoint}")
    String publicEndpoint;

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    public MultipartDto createMultiPart(MultipartRequest request) {
        validateFile(request);

        String targetBucket = requireBucket(request.bucket());
        String safeFileName = sanitizeFileName(request.fileName());
        String date = DateUtil.getNowDateFolder();

        String uuid = UUID.randomUUID().toString();
        String objectKey = uuid
                        + "-"
                        + safeFileName;

        CreateMultipartUploadRequest createRequest = CreateMultipartUploadRequest.builder()
                .bucket(targetBucket)
                .key(objectKey)
                .contentType(request.contentType())
                .build();

        CreateMultipartUploadResponse response = s3Client.createMultipartUpload(createRequest);

        int partCount = Math.toIntExact((request.fileSize() + PART_SIZE - 1) / PART_SIZE);

        if (partCount > MAX_PARTS) {
            abort(targetBucket, response.uploadId(), objectKey);
            throw new IllegalArgumentException("File tạo ra quá nhiều chunk");
        }

        return new MultipartDto(
                response.uploadId(),
                objectKey,
                PART_SIZE,
                partCount,
                request.fileName(),
                request.fileSize(),
                request.contentType(),
                date,
                uuid,
                targetBucket
        );
    }

    public PartUrlDto createPartUrl(PartUrlRequest request) {
        if (request.partNumber() < 1 || request.partNumber() > MAX_PARTS) {
            throw new IllegalArgumentException(
                    "partNumber không hợp lệ"
            );
        }

        UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket(requireBucket(request.bucket()))
                .key(request.objectKey())
                .uploadId(request.uploadId())
                .partNumber(request.partNumber())
                .build();

        UploadPartPresignRequest presignRequest = UploadPartPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(15))
                .uploadPartRequest(uploadPartRequest)
                .build();

        PresignedUploadPartRequest presigned = s3Presigner.presignUploadPart(presignRequest);

        return new PartUrlDto(request.partNumber(), presigned.url().toString());
    }

    public CompleteMultipartDto complete(
            CompleteMultipartRequest request
    ) {
        if (request.parts() == null
                || request.parts().isEmpty()) {
            throw new IllegalArgumentException(
                    "Danh sách part không được để trống"
            );
        }

        List<CompletedPart> completedParts =
                request.parts().stream()
                        .sorted(
                                Comparator.comparingInt(
                                        CompletedPartRequest::partNumber
                                )
                        )
                        .map(part ->
                                CompletedPart.builder()
                                        .partNumber(
                                                part.partNumber()
                                        )
                                        .eTag(part.etag())
                                        .build()
                        )
                        .toList();

        CompletedMultipartUpload multipartUpload =
                CompletedMultipartUpload.builder()
                        .parts(completedParts)
                        .build();

        CompleteMultipartUploadRequest completeRequest =
                CompleteMultipartUploadRequest.builder()
                        .bucket(requireBucket(request.bucket()))
                        .key(request.objectKey())
                        .uploadId(request.uploadId())
                        .multipartUpload(multipartUpload)
                        .build();

        CompleteMultipartUploadResponse response = s3Client.completeMultipartUpload(completeRequest);

        String httpsLocation = response.location()
                .replace(internalEndpoint, publicEndpoint);

        return new CompleteMultipartDto(
                response.bucket() != null
                        ? response.bucket()
                        : requireBucket(request.bucket()),

                response.key() != null
                        ? response.key()
                        : request.objectKey(),

                httpsLocation,
                response.eTag()
        );
    }

    public void abort(
            String targetBucket,
            String uploadId,
            String objectKey
    ) {
        s3Client.abortMultipartUpload(
                AbortMultipartUploadRequest.builder()
                        .bucket(requireBucket(targetBucket))
                        .key(objectKey)
                        .uploadId(uploadId)
                        .build()
        );
    }

    private void validateFile(
            MultipartRequest request
    ) {
        if (!StringUtils.hasText(request.fileName())) {
            throw new IllegalArgumentException(
                    "Tên file không hợp lệ"
            );
        }

        if (request.fileSize() <= 0) {
            throw new IllegalArgumentException(
                    "Dung lượng file không hợp lệ"
            );
        }

        if (request.fileSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException(
                    "File không được vượt quá 1 GB"
            );
        }

        log.info("{}", request.contentType());

        if (!StringUtils.hasText(request.contentType())) {
            throw new IllegalArgumentException(
                    "Content type không hợp lệ"
            );
        }

//        boolean allowed =
//                request.contentType().startsWith("image/")
//                        || request.contentType().startsWith("video/");
//
//        if (!allowed) {
//            throw new IllegalArgumentException(
//                    "Chỉ hỗ trợ file ảnh hoặc video"
//            );
//        }
    }

    private String sanitizeFileName(String fileName) {
        String baseName = fileName
                .replace("\\", "/");

        baseName = baseName.substring(
                baseName.lastIndexOf("/") + 1
        );

        return baseName
                .replaceAll(
                        "[^a-zA-Z0-9._-]",
                        "_"
                );
    }

    private String requireBucket(String requestedBucket) {
        if (!StringUtils.hasText(requestedBucket)) {
            throw new IllegalArgumentException("Bucket không được để trống");
        }

        return requestedBucket.trim();
    }
}