package com.kt.shopping.repository;

import com.kt.shopping.common.api.CustomException;
import com.kt.shopping.common.api.ErrorCode;
import com.kt.shopping.domain.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    default Product findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
    }
}
