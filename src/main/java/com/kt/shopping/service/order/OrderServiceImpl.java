package com.kt.shopping.service.order;

import com.kt.shopping.common.Preconditions;
import com.kt.shopping.common.api.ErrorCode;
import com.kt.shopping.domain.dto.response.order.OrderResponse;
import com.kt.shopping.domain.model.order.Order;
import com.kt.shopping.domain.model.order.Receiver;
import com.kt.shopping.domain.model.orderproduct.OrderProduct;
import com.kt.shopping.domain.model.product.Product;
import com.kt.shopping.domain.model.user.User;
import com.kt.shopping.repository.OrderProductRepository;
import com.kt.shopping.repository.OrderRepository;
import com.kt.shopping.repository.ProductRepository;
import com.kt.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    @Override
    @Transactional
    public void create(
        Long userId,
        Long productId,
        String receiverName,
        String receiverAddress,
        String receiverMobile,
        Long quantity
    ) {
        Product product = productRepository.findByIdOrThrow(productId);

        Preconditions.validate(product.canProvide(quantity), ErrorCode.NOT_ENOUGH_STOCK);

        User user = userRepository.findByIdOrThrow(userId, ErrorCode.NOT_FOUND_USER);

        Receiver receiver = new Receiver(
                receiverName,
                receiverAddress,
                receiverMobile
        );

        Order order = Order.create(receiver, user);
        orderRepository.save(order);

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
