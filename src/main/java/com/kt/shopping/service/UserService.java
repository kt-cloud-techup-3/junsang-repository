package com.kt.shopping.service;

import com.kt.shopping.domain.User;
import com.kt.shopping.domain.dto.request.UserCreateRequest;
import com.kt.shopping.domain.dto.request.UserUpdateRequest;

public interface UserService {

    void create(UserCreateRequest request);

    void update(String userId, UserUpdateRequest request);
}
