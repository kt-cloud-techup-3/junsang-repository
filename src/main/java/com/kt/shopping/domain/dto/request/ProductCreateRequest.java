package com.kt.shopping.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequest(
        @NotBlank
        String name,

        @NotNull
        Long price,

        @NotNull
        Long stock
) {
}
