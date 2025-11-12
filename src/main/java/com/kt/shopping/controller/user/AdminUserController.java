package com.kt.shopping.controller.user;

import com.kt.shopping.common.Paging;
import com.kt.shopping.common.api.ApiResult;
import com.kt.shopping.domain.dto.request.user.UserRequest;
import com.kt.shopping.domain.dto.response.UserResponse;
import com.kt.shopping.domain.model.user.User;

import com.kt.shopping.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
@RestController
public class AdminUserController {

    private final UserService userService;

    @Operation(
        parameters = {
            @Parameter(name = "keyword", description = "검색 키워드(이름)"),
            @Parameter(name = "page", description = "페이지 번호", example = "1"),
            @Parameter(name = "size", description = "페이지 크기", example = "10")
        }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Page<UserResponse.Search>>  search(
        @Parameter(hidden = true) Paging paging,
        @RequestParam(required = false) String keyword
    ) {
        return ApiResult.ok(
                userService.search(paging.toPageable(), keyword)
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
