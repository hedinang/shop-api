package com.example.shop_api.service;

import com.example.shop_api.model.dto.VideoDto;
import com.example.shop_api.model.request.VideoRequest;


public interface VideoService {
    VideoDto extract(VideoRequest request);
}
