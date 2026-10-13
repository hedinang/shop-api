package com.example.shop_api.model.dto;

public record CompleteMultipartDto(
        String bucket,
        String objectKey,
        String location,
        String eTag
) {
}