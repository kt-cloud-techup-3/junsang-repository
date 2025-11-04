package com.kt.shopping.controller;

import com.kt.shopping.domain.User;
import com.kt.shopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin/users")
@RestController
public class AdminUserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User detail(@PathVariable Long id) {
        return userService.detail(id);
    }
}
