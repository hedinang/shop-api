package com.example.shop_api.controller.free;

import com.example.shop_api.model.request.PageRequest;
import com.example.shop_api.model.request.search.SimSearch;
import com.example.shop_api.service.SimService;
import com.example.shop_api.util.response.BaseResponse;
import com.example.shop_api.util.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/free/sim")
public class FreeSimController {
    private final SimService simService;

    @PostMapping("/list")
    public BaseResponse<Object> getList(@RequestBody PageRequest<SimSearch> request) {
        return Response.toData(simService.getList(request));
    }

    @PostMapping("/detail/{simId}")
    public BaseResponse<Object> getList(@PathVariable String simId) {
        return Response.toData(simService.detail(simId));
    }
}
