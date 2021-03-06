package com.pjh.web.shop.repository;

import com.pjh.web.shop.domain.ProductTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTradeRepository extends JpaRepository<ProductTrade, Long> {
}
