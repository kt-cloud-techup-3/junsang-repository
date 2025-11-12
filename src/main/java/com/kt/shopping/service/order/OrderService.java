package com.kt.shopping.service.order;

public interface OrderService {
    void create(
        Long userId,
        Long productId,
        String receiverName,
        String receiverAddress,
        String receiverMobile,
        Long quantity
    );
}
