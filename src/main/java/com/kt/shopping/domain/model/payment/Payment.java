package com.kt.shopping.domain.model.payment;

import com.kt.shopping.constants.PaymentType;
import com.kt.shopping.domain.model.common.BaseEntity;
import com.kt.shopping.domain.model.order.Order;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "payment")
public class Payment extends BaseEntity {

    @Column(nullable = false)
    private Long totalPrice;

    @Column(nullable = false)
    private Long deliveryFee;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @OneToOne
    @JoinColumn(nullable = false)
    private Order order;

}
