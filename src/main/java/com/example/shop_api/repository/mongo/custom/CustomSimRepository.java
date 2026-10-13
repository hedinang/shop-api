package com.example.shop_api.repository.mongo.custom;

import com.example.shop_api.model.entity.Sim;
import com.example.shop_api.model.request.PageRequest;
import com.example.shop_api.model.request.search.SimSearch;

import java.util.List;

public interface CustomSimRepository {
    List<Sim> findBySearch(PageRequest<SimSearch> request);

    Sim findBySimId(String simId);
}
