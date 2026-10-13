package com.example.shop_api.service;

import com.example.shop_api.model.entity.Resource;
import com.example.shop_api.model.entity.User;
import com.example.shop_api.model.request.ResourceRequest;
import com.example.shop_api.model.request.UploadFileRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;

public interface ResourceService {
    File readFile(String resourceId);

    boolean uploadProfileImage(UploadFileRequest req, User user);

    Resource uploadFileChunk(MultipartFile file, ResourceRequest resourceRequest, User user);

    ResponseEntity<StreamingResponseBody> streamFileDirectly(String resourceId, User meDto, String rangeHeader, boolean isDownload);
}
