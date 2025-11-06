package com.kt.shopping.service;

import com.kt.shopping.domain.dto.request.ProductCreateRequest;
import com.kt.shopping.domain.dto.response.ProductCreateResponse;

public interface ProductService {

    ProductCreateResponse create(ProductCreateRequest request);

}
