package com.kt.shopping.domain.model;

import com.kt.shopping.constants.ProductStatus;
import com.kt.shopping.domain.model.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Product extends BaseEntity {

    private String name;
    private Long price;
    private Long stock;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts = new ArrayList<>();
}
