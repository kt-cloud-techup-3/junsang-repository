package com.kt.shopping.domain.dto.request;

import java.time.LocalDate;

public record UserCreateRequest(
        String loginId,
        String password,
        String name,
        LocalDate birthday
) {
}
