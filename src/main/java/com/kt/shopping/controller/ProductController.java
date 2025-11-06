package com.kt.shopping.controller;

import com.kt.shopping.domain.dto.request.ProductCreateRequest;
import com.kt.shopping.domain.dto.response.ProductCreateResponse;
import com.kt.shopping.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductCreateResponse create(
            @RequestBody @Valid ProductCreateRequest request) {
        return productService.create(request);
    }

}
