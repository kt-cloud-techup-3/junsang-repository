package com.kt.shopping.repository;

import com.kt.shopping.domain.dto.response.order.OrderResponse;
import com.kt.shopping.domain.dto.response.order.QOrderResponse_Search;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.kt.shopping.domain.model.order.QOrder.order;
import static com.kt.shopping.domain.model.orderproduct.QOrderProduct.orderProduct;
import static com.kt.shopping.domain.model.product.QProduct.product;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<OrderResponse.Search> search(
            String keyword,
            Pageable pageable
    ) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(containsProductName(keyword));

        var content = queryFactory
                .select(new QOrderResponse_Search(
                        order.id,
                        order.receiver.name,
                        product.name,
                        orderProduct.quantity,
                        product.price.multiply(orderProduct.quantity),
                        order.status,
                        order.createdAt
                ))
                .from(order)
                .join(orderProduct).on(orderProduct.order.id.eq(order.id))
                .join(product).on(orderProduct.product.id.eq(product.id))
                .where(booleanBuilder)
                .orderBy(order.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        var total = (long) queryFactory.select(order.id)
                .from(order)
                .join(orderProduct).on(orderProduct.order.id.eq(order.id))
                .join(product).on(orderProduct.product.id.eq(product.id))
                .where(booleanBuilder)
                .fetch().size();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression containsProductName(String keyword) {
        return Strings.isNotBlank(keyword) ? product.name.containsIgnoreCase(keyword) : null;
    }


}
