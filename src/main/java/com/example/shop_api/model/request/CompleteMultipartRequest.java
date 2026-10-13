package com.example.shop_api.model.request;

import java.util.List;

public record CompleteMultipartRequest(
        String uploadId,
        String objectKey,
        List<CompletedPartRequest> parts
) {
}