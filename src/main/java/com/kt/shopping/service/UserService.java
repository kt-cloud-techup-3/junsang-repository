package com.kt.shopping.service;

import com.kt.shopping.domain.User;
import com.kt.shopping.domain.dto.request.UserCreateRequest;

public interface UserService {

    void create(UserCreateRequest request);
}
