package com.example.shop_api.model.request;

public record AbortRequest(
        String bucket,
        String uploadId,
        String objectKey
) {
}
