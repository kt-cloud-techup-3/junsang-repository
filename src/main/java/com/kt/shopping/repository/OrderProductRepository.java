package com.kt.shopping.repository;

import com.kt.shopping.domain.model.orderproduct.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}

