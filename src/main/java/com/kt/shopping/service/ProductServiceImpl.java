package com.kt.shopping.service;

import com.kt.shopping.domain.model.product.Product;
import com.kt.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void create(String name, Long price, Long quantity) {
        Product product = Product.create(name, price, quantity);
        productRepository.save(product);
    }

    @Override
    public void soldOut(Long id) {
        Product product = productRepository.findByIdOrThrow(id);
        product.soldOut();
    }

    @Override
    public void inActivate(Long id) {
        Product product = productRepository.findByIdOrThrow(id);
        product.inActivate();
    }

    @Override
    public void activate(Long id) {
        Product product = productRepository.findByIdOrThrow(id);
        product.activate();
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findByIdOrThrow(id);
        product.delete();
    }
}
