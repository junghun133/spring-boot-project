package com.pjh.test.daou.repository;

import com.pjh.test.daou.domain.OrderLine;
import com.pjh.test.daou.repository.dsl.OrderLineRepositoryQD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long>, OrderLineRepositoryQD {
}
