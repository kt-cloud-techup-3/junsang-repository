package com.kt.shopping.controller;

import com.kt.shopping.domain.User;
import com.kt.shopping.domain.dto.request.UserCreateRequest;
import com.kt.shopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping
    public void create(@RequestBody UserCreateRequest request) {
        userService.create(request);
    }
}
