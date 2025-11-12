package com.kt.shopping.controller.user;

import com.kt.shopping.common.api.ApiResult;
import com.kt.shopping.domain.dto.request.UserCreateRequest;
import com.kt.shopping.domain.dto.request.user.UserRequest;
import com.kt.shopping.domain.dto.request.user.UserUpdatePasswordRequest;
import com.kt.shopping.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저", description = "유저 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "유효성 검사 실패"),
        @ApiResponse(responseCode = "500", description = "서버 에러 - 백엔드에 바로 문의 바랍니다.")
})
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<Void> create(
            @RequestBody @Valid UserRequest.Create request
    ) {
        userService.create(request);
        return ApiResult.ok();
    }

    @GetMapping("/duplicate-login-id")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Boolean> isDuplicateLoginId(@RequestParam String loginId) {
        return ApiResult.ok(
            userService.isDuplicateLoginId(loginId)
        );

    }

    @PutMapping("/{id}/update-password")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody @Valid UserUpdatePasswordRequest request) {
        userService.changePassword(id, request.oldPassword(), request.newPassword());
        return ApiResult.ok();
    }

}
