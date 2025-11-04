package com.kt.shopping.controller;

import com.kt.shopping.domain.dto.request.UserCreateRequest;
import com.kt.shopping.domain.dto.request.UserUpdatePasswordRequest;
import com.kt.shopping.service.UserService;
import jakarta.validation.Valid;
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
    public void create(@RequestBody @Valid UserCreateRequest request) {
        userService.create(request);
    }

    @GetMapping("/duplicate-login-id")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isDuplicateLoginId(@RequestParam String loginId) {
        return userService.isDuplicateLoginId(loginId);
    }

    @PutMapping("/{id}/update-password")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(
            @PathVariable Integer id,
            @RequestBody @Valid UserUpdatePasswordRequest request) {
        userService.changePassword(id, request.oldPassword(), request.newPassword());
    }

}
