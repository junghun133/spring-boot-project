package com.pjh.test.daou.repository;

import com.pjh.test.daou.domain.ProductModifyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductModifyHistoryRepository extends JpaRepository<ProductModifyHistory, Long> {
}
