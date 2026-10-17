package com.example.shop_api.model.request;

import java.util.List;

public record CompleteMultipartRequest(
        String bucket,
        String uploadId,
        String objectKey,
        List<CompletedPartRequest> parts,
        String fileName,
        int fileSize,
        String contentType,
        String date,
        String uuid,
        Integer width,
        Integer height
) {
}