package com.kt.shopping.domain.dto.response;

import com.kt.shopping.domain.User;

import java.util.List;

public record CustomPage(
    List<User> users,
    int size,
    int page,
    int pages,
    long totalElements
) {
}
