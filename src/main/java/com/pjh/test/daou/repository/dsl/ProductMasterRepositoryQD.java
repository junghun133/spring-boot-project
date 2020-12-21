package com.pjh.test.daou.repository.dsl;

import com.pjh.test.daou.domain.ProductMaster;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface ProductMasterRepositoryQD {
    List<ProductMaster> selectProductList(int offset, int limit, String keyword);
}
