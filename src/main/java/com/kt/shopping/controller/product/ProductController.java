package com.kt.shopping.controller.product;

import com.kt.shopping.common.api.ApiResult;
import com.kt.shopping.domain.dto.request.product.ProductRequest;
import com.kt.shopping.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Void> create(
            @RequestBody @Valid ProductRequest.Create request
    ) {
        productService.create(
                request.name(),
                request.price(),
                request.quantity()
        );
        return ApiResult.ok();
    }

    @PatchMapping("/{id}/sold-out")
    public void soldOut(@PathVariable Long id) {
        productService.soldOut(id);
    }

    @PatchMapping("/{id}/activate")
    public ApiResult<Void> activate(@PathVariable Long id) {
        productService.activate(id);

        return ApiResult.ok();
    }

    @PatchMapping("/{id}/in-activate")
    public ApiResult<Void> inActivate(@PathVariable Long id) {
        productService.inActivate(id);

        return ApiResult.ok();
    }

    @DeleteMapping("/{id}")
    public ApiResult<Void> remove(@PathVariable Long id) {
        productService.delete(id);

        return ApiResult.ok();
    }



}
