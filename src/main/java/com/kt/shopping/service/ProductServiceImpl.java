package com.kt.shopping.service;

import com.kt.shopping.domain.dto.request.ProductCreateRequest;
import com.kt.shopping.domain.dto.request.product.ProductRequest;
import com.kt.shopping.domain.dto.response.ProductCreateResponse;
import com.kt.shopping.domain.model.product.Product;
import com.kt.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void create(String name, Long price, Long quantity) {
        Product product = Product.create(name, price, quantity);
        productRepository.save(product);
    }
}
