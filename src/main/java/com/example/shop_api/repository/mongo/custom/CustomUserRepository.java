package com.example.shop_api.repository.mongo.custom;

import com.example.shop_api.model.entity.User;
import com.example.shop_api.model.request.PageRequest;
import com.example.shop_api.model.search.UserSearch;

import java.util.List;

public interface CustomUserRepository {
    List<User> getUserList(PageRequest<UserSearch> request);

    long countUserList(UserSearch request);

    boolean updateAvatarByUserId(String userId, String avatar);
}
