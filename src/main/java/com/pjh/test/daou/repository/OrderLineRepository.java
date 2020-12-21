package com.pjh.test.daou.repository;

import com.pjh.test.daou.domain.OrderLine;
import com.pjh.test.daou.repository.dsl.OrderLineRepositoryQD;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long>, OrderLineRepositoryQD {
    @Override
    @EntityGraph(attributePaths = {"productMaster", "productTrade"})
    List<OrderLine> findAll();
}
