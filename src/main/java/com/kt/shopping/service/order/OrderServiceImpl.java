package com.kt.shopping.service.order;

import com.kt.shopping.common.Lock;
import com.kt.shopping.common.Preconditions;
import com.kt.shopping.common.api.ErrorCode;
import com.kt.shopping.domain.dto.response.order.OrderResponse;
import com.kt.shopping.domain.model.order.Order;
import com.kt.shopping.domain.model.order.Receiver;
import com.kt.shopping.domain.model.orderproduct.OrderProduct;
import com.kt.shopping.repository.OrderProductRepository;
import com.kt.shopping.repository.OrderRepository;
import com.kt.shopping.repository.ProductRepository;
import com.kt.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final RedissonClient redissonClient;

    @Lock(key = Lock.Key.STOCK, index = 1)
    public void create(
            Long userId,
            Long productId,
            String receiverName,
            String receiverAddress,
            String receiverMobile,
            Long quantity
    ) {
        // var product = productRepository.findByIdPessimistic(productId).orElseThrow();
        var product = productRepository.findByIdOrThrow(productId);

        System.out.println(product.getStock());
        Preconditions.validate(product.canProvide(quantity), ErrorCode.NOT_ENOUGH_STOCK);

        var user = userRepository.findByIdOrThrow(userId, ErrorCode.NOT_FOUND_USER);

        var receiver = new Receiver(
                receiverName,
                receiverAddress,
                receiverMobile
        );

        var order = orderRepository.save(Order.create(receiver, user));
        OrderProduct orderProduct = OrderProduct.create(order, product, quantity);
        orderProductRepository.save(orderProduct);

        product.decreaseStock(quantity);

        product.mapToOrderProduct(orderProduct);
        order.mapToOrderProduct(orderProduct);
    }

    @Override
    public Page<OrderResponse.Search> search(
            String keyword,
            Pageable pageable
    ) {
        return orderRepository.search(keyword, pageable);
    }
}
