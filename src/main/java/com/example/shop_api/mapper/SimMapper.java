package com.example.shop_api.mapper;

import com.example.shop_api.model.dto.DaySimDto;
import com.example.shop_api.model.dto.SimDto;
import com.example.shop_api.model.entity.SimPackage;
import com.example.shop_api.model.entity.Sim;
import com.example.shop_api.model.request.SimRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimMapper {
    Sim simRequestToSim(SimRequest request);

    SimPackage simRequestToDaySim(SimRequest request);

    SimDto simToSimDto(Sim sim);

    DaySimDto daySimToDaySimDto(SimPackage simPackage);
}
