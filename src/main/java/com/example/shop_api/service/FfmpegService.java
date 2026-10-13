package com.example.shop_api.service;

import com.example.shop_api.model.dto.FfmpegDto;

import java.io.IOException;
import java.util.List;

public interface FfmpegService {
    FfmpegDto runCommand(List<String> command) throws IOException, InterruptedException;
}
