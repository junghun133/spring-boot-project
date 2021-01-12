package com.pjh.web.shop.repository;

import com.pjh.web.shop.domain.ProductMaster;
import com.pjh.web.shop.repository.dsl.ProductMasterRepositoryQD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long>, ProductMasterRepositoryQD {
    Page<ProductMaster> findByNameContains(String name, Pageable pageable);

    @Query(value = "select p from ProductMaster p left join fetch p.attachmentImage",
            countQuery = "select count(p) from ProductMaster p")
    public Page<ProductMaster> findAllWithFetch(Pageable pageable);

//    @Query(value = "select p from ProductMaster p  join fetch p.attachmentImage")
    @EntityGraph(attributePaths = {"attachmentImage"})
    Optional<ProductMaster> findProductById(Long id);
}
