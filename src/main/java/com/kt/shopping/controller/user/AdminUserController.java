package com.kt.shopping.controller.user;

import com.kt.shopping.common.api.ApiResult;
import com.kt.shopping.domain.dto.request.user.UserRequest;
import com.kt.shopping.domain.model.user.User;

import com.kt.shopping.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
@RestController
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Page<User>> search(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        return ApiResult.ok(
                userService.search(
                        PageRequest.of(page - 1 , size), keyword
                )
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<User> detail(@PathVariable Long id) {
        return ApiResult.ok(userService.detail(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Void> update(
            @PathVariable Long id,
            @RequestBody @Valid UserRequest.Update request) {
        userService.update(id, request.name(), request.email(), request.mobile());
        return ApiResult.ok();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ApiResult.ok();
    }

}
