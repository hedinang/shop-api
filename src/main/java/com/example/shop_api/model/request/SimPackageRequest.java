package com.example.shop_api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimPackageRequest {
    private String simId;
    private String time;
    private String unit;
}
