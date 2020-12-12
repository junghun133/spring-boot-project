package com.pjh.test.daou.repository;

import com.pjh.test.daou.domain.ProductTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTradeRepository extends JpaRepository<ProductTrade, Long> {
}
