package com.kt.shopping.service.order;

import com.kt.shopping.domain.dto.response.order.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    void create(
        Long userId,
        Long productId,
        String receiverName,
        String receiverAddress,
        String receiverMobile,
        Long quantity
    );

    Page<OrderResponse.Search> search(String keyword, Pageable pageable);
}
