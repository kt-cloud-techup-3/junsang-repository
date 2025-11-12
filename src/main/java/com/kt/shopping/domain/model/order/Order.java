package com.kt.shopping.domain.model.order;

import com.kt.shopping.constants.OrderStatus;
import com.kt.shopping.domain.model.orderproduct.OrderProduct;
import com.kt.shopping.domain.model.user.User;
import com.kt.shopping.domain.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity(name = "\"order\"")
@NoArgsConstructor(access = PROTECTED)
public class Order extends BaseEntity {

    @Embedded
    private Receiver receiver;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime deliveredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    private Order(Receiver receiver, User user) {
        this.receiver = receiver;
        this.user = user;
        this.deliveredAt = LocalDateTime.now().plusDays(3);
        this.status = OrderStatus.PENDING;
    }

    public static Order create(Receiver receiver, User user) {
        return new Order(
                receiver,
                user
        );
    }

    public void mapToOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
    }
}
