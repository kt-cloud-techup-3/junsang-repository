package com.kt.shopping.domain.dto.response.order;

import com.kt.shopping.constants.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public class OrderResponse {

    public record Search(
        Long id,
        String receiverName,
        String productName,
        Long quantity,
        Long totalPrice,
        OrderStatus status,
        LocalDateTime createdAt
    ) {
        @QueryProjection
        public Search {
        }
    }
}

