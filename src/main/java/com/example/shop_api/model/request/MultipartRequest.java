package com.example.shop_api.model.request;

public record MultipartRequest(
        String bucket,
        String fileName,
        int fileSize,
        String contentType
) {
}