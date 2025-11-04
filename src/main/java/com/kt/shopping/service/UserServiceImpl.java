package com.kt.shopping.service;

import com.kt.shopping.domain.User;
import com.kt.shopping.domain.dto.request.UserCreateRequest;
import com.kt.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void create(UserCreateRequest request) {
        System.out.println(request.toString());
        var newUser = new User(
                userRepository.selectMaxId() + 1,
                request.loginId(),
                request.password(),
                request.name(),
                request.email(),
                request.mobile(),
                request.gender(),
                request.birthday(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        userRepository.save(newUser);
    }

    @Override
    public boolean isDuplicateLoginId(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }
}
