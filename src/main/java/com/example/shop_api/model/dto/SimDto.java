package com.example.shop_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimDto {
    private String simId;
    private String name;
    private String title;
    private List<String> advantages;
    private String type;
    private String brand;
    private String nation;
    private List<DaySimDto> daySims;
}
