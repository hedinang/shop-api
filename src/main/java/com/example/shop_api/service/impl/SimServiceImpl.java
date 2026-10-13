package com.example.shop_api.service.impl;

import com.example.shop_api.mapper.SimMapper;
import com.example.shop_api.model.dto.SimDto;
import com.example.shop_api.model.entity.SimPackage;
import com.example.shop_api.model.entity.Sim;
import com.example.shop_api.model.request.PageRequest;
import com.example.shop_api.model.request.SimRequest;
import com.example.shop_api.model.request.search.SimSearch;
import com.example.shop_api.repository.mongo.DaySimRepository;
import com.example.shop_api.repository.mongo.SimRepository;
import com.example.shop_api.service.SimService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimServiceImpl implements SimService {
    private final SimMapper simMapper;
    private final SimRepository simRepository;
    private final DaySimRepository daySimRepository;

    public SimDto create(SimRequest request) {
        Sim sim = simMapper.simRequestToSim(request);
        Sim newSim = simRepository.insert(sim);

//        if (request.getDaySimRequests() == null || request.getDaySimRequests().isEmpty()) {
//            return simMapper.simToSimDto(newSim);
//        }
//
//        List<SimPackage> simPackageList = request.getDaySimRequests().stream().map(daySimRequest -> {
//            SimPackage simPackage = simMapper.simRequestToDaySim(request);
//            simPackage.setSimId(newSim.getSimId());
//            return simPackage;
//        }).toList();
//
//        List<SimPackage> newSimPackages = daySimRepository.insert(simPackageList);

        SimDto simDto = simMapper.simToSimDto(newSim);
//        simDto.setDaySims(newSimPackages.stream().map(simMapper::daySimToDaySimDto).toList());

        return simDto;
    }

    public List<Sim> getList(PageRequest<SimSearch> request) {
        return simRepository.findBySearch(request);
    }

    public Sim detail(String simId) {
        return simRepository.findBySimId(simId);
    }
}
