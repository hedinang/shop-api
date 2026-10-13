package com.example.shop_api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusRequest {
    private String date;
    private String targetType;
    private String destinationType;
}
