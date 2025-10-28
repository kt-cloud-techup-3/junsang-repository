package com.kt.shopping.controller;

import com.kt.shopping.domain.User;
import com.kt.shopping.domain.dto.request.UserCreateRequest;
import com.kt.shopping.domain.dto.request.UserUpdateRequest;
import com.kt.shopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping
    public void create(@RequestBody UserCreateRequest request) {
        userService.create(request);
    }

    @PutMapping("/{userId}")
    public void update(@PathVariable String userId,
                       @RequestBody UserUpdateRequest update) {
        userService.update(userId, update);
    }

    @GetMapping("/{userId}")
    public User detail(@PathVariable String userId) {
        return userService.detail(userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId) {
        userService.delete(userId);
    }
}
