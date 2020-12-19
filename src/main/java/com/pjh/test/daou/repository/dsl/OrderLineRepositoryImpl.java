package com.pjh.test.daou.repository.dsl;

import com.pjh.test.daou.http.entity.OrderRank;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.pjh.test.daou.domain.QOrderLine.orderLine;

public class OrderLineRepositoryImpl implements OrderLineRepositoryQD{
    private final JPAQueryFactory queryFactory;
    private EntityManager em;

    public OrderLineRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }

    @Override
    public List<OrderRank> select5CountGroupByProductMasterSumCount() {
        return queryFactory
                .select(Projections.fields(OrderRank.class,
                        orderLine.count.sum().as("orderCount"),
                        orderLine.productMaster.name.as("productName")
                        )
                )
                .from(orderLine)
                .groupBy(orderLine.productMaster)
                .orderBy(orderLine.count.sum().desc())
                .limit(5)
                .fetch();
    }

    @Override
    public Integer sumByOrderCount() {
         return queryFactory
                .select(orderLine.count.sum().castToNum(Integer.class))
                .from(orderLine).fetchOne();
    }
}
