package com.kt.shopping.controller.order;

import com.kt.shopping.common.api.ApiResult;
import com.kt.shopping.domain.dto.request.order.OrderRequest;
import com.kt.shopping.security.CurrentUser;
import com.kt.shopping.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //주문생성
    @PostMapping
    public ApiResult<Void> create(
            @AuthenticationPrincipal CurrentUser currentUser,
            @RequestBody @Valid OrderRequest.Create request) {
        orderService.create(
                currentUser.getId(),
                request.productId(),
                request.receiverName(),
                request.receiverAddress(),
                request.receiverMobile(),
                request.quantity()
        );
        return ApiResult.ok();
    }
}

