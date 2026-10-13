package com.example.shop_api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimRequest {
    private String simId;
    private String name;
    private String title;
    private List<String> advantages;
    private String type;
    private String brand;
    private String nation;

    private List<SimPackageRequest> simPackageRequests;
}
