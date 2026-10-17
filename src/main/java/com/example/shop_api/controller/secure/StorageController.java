package com.example.shop_api.controller.secure;

import com.example.shop_api.model.dto.CompleteMultipartDto;
import com.example.shop_api.model.dto.MultipartDto;
import com.example.shop_api.model.dto.PartUrlDto;
import com.example.shop_api.model.request.AbortRequest;
import com.example.shop_api.model.request.CompleteMultipartRequest;
import com.example.shop_api.model.request.MultipartRequest;
import com.example.shop_api.model.request.PartUrlRequest;
import com.example.shop_api.service.StorageService;
import com.example.shop_api.util.response.BaseResponse;
import com.example.shop_api.util.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secure/storage")
public class StorageController {

    private final StorageService storageService;

    @PostMapping("/create-multipart")
    public BaseResponse<MultipartDto> createMultipart(@RequestBody MultipartRequest request) {
        return Response.toData(storageService.createMultiPart(request));
    }

    @PostMapping("/create-part-url")
    public BaseResponse<PartUrlDto> partUrl(@RequestBody PartUrlRequest request) {
        return Response.toData(storageService.createPartUrl(request));
    }

    @PostMapping("/complete")
    public BaseResponse<CompleteMultipartDto> complete(@RequestBody CompleteMultipartRequest request) {
        return Response.toData(storageService.complete(request));
    }

    @PostMapping("/abort")
    public BaseResponse<Void> abort(@RequestBody AbortRequest request) {
        storageService.abort(request.bucket(), request.uploadId(), request.objectKey());
        return null;
    }
}