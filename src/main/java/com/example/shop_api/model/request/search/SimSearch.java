package com.example.shop_api.model.request.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimSearch {
    //    private String simId;
//    private String name;
//    private String title;
    private String time;
    private String pack;
    private String unit;
    private String type;
    private String brand;
    private String nation;
}
