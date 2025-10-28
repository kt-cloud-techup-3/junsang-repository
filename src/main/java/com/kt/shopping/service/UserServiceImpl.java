package com.kt.shopping.service;

import com.kt.shopping.domain.User;
import com.kt.shopping.domain.dto.request.UserCreateRequest;
import com.kt.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void create(UserCreateRequest request) {
        User user = User.create(request);
        userRepository.save(user);

    }
}
