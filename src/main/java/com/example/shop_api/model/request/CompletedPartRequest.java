package com.example.shop_api.model.request;

public record CompletedPartRequest(
        int partNumber,
        String etag
) {
}