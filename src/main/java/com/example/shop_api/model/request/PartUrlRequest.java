package com.example.shop_api.model.request;

public record PartUrlRequest(
        String bucket,
        String uploadId,
        String objectKey,
        int partNumber
) {
}
