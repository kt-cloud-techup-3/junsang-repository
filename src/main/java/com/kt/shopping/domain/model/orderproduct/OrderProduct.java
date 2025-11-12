package com.kt.shopping.domain.model.orderproduct;

import com.kt.shopping.domain.model.common.BaseEntity;
import com.kt.shopping.domain.model.order.Order;
import com.kt.shopping.domain.model.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class OrderProduct extends BaseEntity {

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected OrderProduct(Order order, Product product, Long quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public static OrderProduct create(
            Order order,
            Product product,
            Long quantity) {
        return new OrderProduct(
                order,
                product,
                quantity
        );
    }


}
