package com.example.shop_api.controller.secure;

import com.example.shop_api.model.request.SimPackageRequest;
import com.example.shop_api.model.request.SimRequest;
import com.example.shop_api.service.SimService;
import com.example.shop_api.util.response.BaseResponse;
import com.example.shop_api.util.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secure/sim-package")
public class SimPackageController {
//    private final SimPackageService simService;
//
//    @PostMapping("/create")
//    public BaseResponse<Object> create(@RequestBody SimPackageRequest request) {
//        return Response.toData(simService.create(request));
//    }
//
//    @PostMapping("/update")
//    public BaseResponse<Object> update(@RequestBody SimRequest request) {
//        return Response.toData(simService.create(request));
//    }
}
