package com.example.shop_api.model.request;

public record MultipartRequest(
        String fileName,
        long fileSize,
        String contentType
) {
}