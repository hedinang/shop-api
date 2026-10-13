package com.example.shop_api.model.request;

public record AbortRequest(
        String uploadId,
        String objectKey
) {
}
