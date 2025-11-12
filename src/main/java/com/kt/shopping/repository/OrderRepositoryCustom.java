package com.kt.shopping.repository;

import com.kt.shopping.domain.dto.response.order.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {
    Page<OrderResponse.Search> search(String keyword, Pageable pageable);
}
