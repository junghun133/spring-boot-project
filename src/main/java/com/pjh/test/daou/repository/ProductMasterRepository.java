package com.pjh.test.daou.repository;

import com.pjh.test.daou.domain.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long> {
//    List<ProductMaster> findAll(ProductSearch productSearch);
}
