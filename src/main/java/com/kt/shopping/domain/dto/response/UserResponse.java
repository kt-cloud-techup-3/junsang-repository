package com.kt.shopping.domain.dto.response;

import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class UserResponse {

    public record Search(
            Long id,
            String name,
            LocalDateTime createdAt
    ) {
    }

}
