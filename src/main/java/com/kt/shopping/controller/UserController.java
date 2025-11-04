package com.kt.shopping.controller;

import com.kt.shopping.domain.dto.request.UserCreateRequest;
import com.kt.shopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserCreateRequest request) {
        userService.create(request);

    }
}
