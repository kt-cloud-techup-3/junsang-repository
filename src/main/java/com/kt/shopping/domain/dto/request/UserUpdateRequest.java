package com.kt.shopping.domain.dto.request;

public record UserUpdateRequest(
        String name,
        String password,
        String birthday
) {
}
