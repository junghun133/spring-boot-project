package com.pjh.web.shop.repository;

import com.pjh.web.shop.domain.ProductModifyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductModifyHistoryRepository extends JpaRepository<ProductModifyHistory, Long> {
}
