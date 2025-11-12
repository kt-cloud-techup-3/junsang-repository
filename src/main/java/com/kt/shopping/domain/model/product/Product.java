package com.kt.shopping.domain.model.product;

import com.kt.shopping.constants.ProductStatus;
import com.kt.shopping.domain.model.orderproduct.OrderProduct;
import com.kt.shopping.domain.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@ToString(exclude = {"orderProducts"})
@NoArgsConstructor(access = PROTECTED)
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(nullable = false)
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    protected Product(String name, Long price, Long stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.status = ProductStatus.ACTIVATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String name, Long price, Long stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void soldOut() {
        this.status = ProductStatus.SOLD_OUT;
    }

    public void inActivate() {
        this.status = ProductStatus.IN_ACTIVATED;
    }

    public void activate() {
        this.status = ProductStatus.ACTIVATED;
    }

    public void delete() {
        // 논리삭제
        this.status = ProductStatus.DELETED;
    }

    public void decreaseStock(Long quantity) {
        this.stock -= quantity;
    }

    public void increaseStock(Long quantity) {
        this.stock += quantity;
    }

    public boolean canProvide(Long quantity) {
        return this.stock >= quantity;
    }

    public void mapToOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
    }

    public static Product create(final String name,
                                 final Long price,
                                 final Long stock) {
        return new Product(name, price, stock);
    }
}
