package com.kt.shopping.controller;

import com.kt.shopping.domain.User;
import com.kt.shopping.domain.dto.request.UserUpdateRequest;
import com.kt.shopping.domain.dto.response.CustomPage;
import com.kt.shopping.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin/users")
@RestController
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomPage search(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        return userService.search(page, size, keyword);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User detail(@PathVariable Long id) {
        return userService.detail(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id,
            @RequestBody @Valid UserUpdateRequest request) {
        userService.update(id, request.name(), request.email(), request.mobile());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
