package com.example.shop_api.model.dto;

public record MultipartDto(
        String uploadId,
        String objectKey,
        long partSize,
        int partCount,
        String fileName,
        int fileSize,
        String contentType,
        String date,
        String uuid,
        String bucket
) {
}