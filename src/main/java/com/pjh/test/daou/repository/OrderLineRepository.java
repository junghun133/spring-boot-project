package com.pjh.test.daou.repository;

import com.pjh.test.daou.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
