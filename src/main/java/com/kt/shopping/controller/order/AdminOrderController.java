package com.kt.shopping.controller.order;

import com.kt.shopping.common.Paging;
import com.kt.shopping.common.api.ApiResult;
import com.kt.shopping.domain.dto.response.order.OrderResponse;
import com.kt.shopping.repository.OrderRepository;
import com.kt.shopping.service.order.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderService orderService;

    @GetMapping
    public ApiResult<Page<OrderResponse.Search>> search(
            @RequestParam(required = false) String keyword,
            @Parameter(hidden = true) Paging paging
    ) {

        return ApiResult.ok(orderService.search(keyword, paging.toPageable()));
    }
}
