package com.example.shop_api.service;

import com.example.shop_api.model.dto.CompleteMultipartDto;
import com.example.shop_api.model.dto.MultipartDto;
import com.example.shop_api.model.dto.PartUrlDto;
import com.example.shop_api.model.request.CompleteMultipartRequest;
import com.example.shop_api.model.request.MultipartRequest;
import com.example.shop_api.model.request.PartUrlRequest;

public interface StorageService {
    MultipartDto createMultiPart(MultipartRequest request);

    PartUrlDto createPartUrl(PartUrlRequest request);

    CompleteMultipartDto complete(CompleteMultipartRequest request);

    void abort(String bucket, String uploadId, String objectKey);
}
