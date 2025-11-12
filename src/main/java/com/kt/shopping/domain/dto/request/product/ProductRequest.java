package com.kt.shopping.domain.dto.request.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class ProductRequest {

    @Schema(name = "ProductRequest.Create")
    public record Create(
        @NotBlank
        String name,
        @NotNull
        Long price,
        @NotNull
        Long quantity
    ) {
    }

    @Schema(name = "ProductRequest.Update")
    public record Update(
        @NotBlank
        String name,
        @NotNull
        Long price,
        @NotNull
        Long quantity
    ) {

    }
}
