package com.example.shop_api.service;

import com.example.shop_api.model.dto.CompleteMultipartDto;
import com.example.shop_api.model.dto.MultipartDto;
import com.example.shop_api.model.dto.PartUrlDto;
import com.example.shop_api.model.request.CompleteMultipartRequest;
import com.example.shop_api.model.request.MultipartRequest;

public interface StorageService {
    MultipartDto createMultiPart(MultipartRequest request);

    PartUrlDto createPartUrl(String uploadId, String objectKey, int partNumber);

    CompleteMultipartDto complete(CompleteMultipartRequest request);

    void abort(String uploadId, String objectKey);
}
