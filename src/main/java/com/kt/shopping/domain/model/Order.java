package com.kt.shopping.domain.model;

import com.kt.shopping.constants.OrderStatus;
import com.kt.shopping.domain.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "\"order\"")
@Getter
public class Order extends BaseEntity {

    private String receiverName;
    private String receiverAddress;
    private String receiverMobile;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime deliveredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts = new ArrayList<>();
}
