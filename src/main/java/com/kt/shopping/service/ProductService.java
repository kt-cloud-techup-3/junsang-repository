package com.kt.shopping.service;


public interface ProductService {

    void create(String name, Long price, Long quantity);

    void update(Long id, String name, Long price, Long quantity);

    void soldOut(Long id);

    void inActivate(Long id);

    void activate(Long id);

    void delete(Long id);
}
