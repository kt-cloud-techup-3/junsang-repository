package com.kt.shopping.service;

import com.kt.shopping.domain.User;
import com.kt.shopping.domain.dto.request.UserCreateRequest;
import com.kt.shopping.domain.dto.response.CustomPage;

public interface UserService {

    void create(UserCreateRequest request);

    boolean isDuplicateLoginId(String loginId);

    void changePassword(int id, String oldPassword, String password);

    User detail(Long id);

    void update(Long id, String name, String email, String mobile);

    void delete(Long id);

    CustomPage search(int page, int size, String keyword);

}
