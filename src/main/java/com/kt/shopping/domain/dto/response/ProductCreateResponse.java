package com.kt.shopping.domain.dto.response;

public record ProductCreateResponse(
        String name,
        Long price,
        Long stock
) {
}
