package com.pjh.web.shop.repository;

import com.pjh.web.shop.domain.OrderLine;
import com.pjh.web.shop.repository.dsl.OrderLineRepositoryQD;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long>, OrderLineRepositoryQD {
    @Override
    @EntityGraph(attributePaths = {"productMaster", "productTrade"})
    List<OrderLine> findAll();
}
