package com.example.shop_api.mapper;

import com.example.shop_api.model.entity.User;
import com.example.shop_api.model.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRequestToUser(UserRequest userRequest);
}
