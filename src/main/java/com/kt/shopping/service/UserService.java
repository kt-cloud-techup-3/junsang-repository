package com.kt.shopping.service;

import com.kt.shopping.domain.dto.request.user.UserRequest;
import com.kt.shopping.domain.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void create(UserRequest.Create request);

    boolean isDuplicateLoginId(String loginId);

    void changePassword(Long id, String oldPassword, String password);

    User detail(Long id);

    void update(Long id, String name, String email, String mobile);

    void delete(Long id);

    Page<User> search(Pageable pageable, String keyword);

}
