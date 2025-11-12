package com.kt.shopping.domain.dto.request.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class OrderRequest {

    @Schema(name = "OrderRequest.Create")
    record Create(
        @NotNull
        Long productId,
        @NotNull
        @Min(1)
        Long quantity,
        @NotBlank
        String receiverName,
        @NotBlank
        String receiverAddress,
        @NotBlank
        String receiverMobile
    ) {
    }
}
