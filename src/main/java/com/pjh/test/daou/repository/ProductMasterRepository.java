package com.pjh.test.daou.repository;

import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.repository.dsl.ProductMasterRepositoryQD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long>, ProductMasterRepositoryQD {
    Page<ProductMaster> findByNameContains(String name, Pageable pageable);
}
