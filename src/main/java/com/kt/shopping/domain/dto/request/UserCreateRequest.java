package com.kt.shopping.domain.dto.request;

import com.kt.shopping.constants.user.Gender;

import java.time.LocalDate;

public record UserCreateRequest(
    String loginId,
    String password,
    String name,
    String email,
    String mobile,
    Gender gender,
    LocalDate birthday
) {
}
