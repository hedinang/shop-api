package com.example.shop_api.service;

import com.example.shop_api.model.dto.SimDto;
import com.example.shop_api.model.entity.Sim;
import com.example.shop_api.model.request.PageRequest;
import com.example.shop_api.model.request.SimRequest;
import com.example.shop_api.model.request.search.SimSearch;

import java.util.List;

public interface SimService {
    SimDto create(SimRequest request);

    List<Sim> getList(PageRequest<SimSearch> request);

    Sim detail(String simId);
}
