package com.pjh.test.daou.repository.dsl;

import com.pjh.test.daou.domain.ProductMaster;

import java.util.List;

public interface ProductMasterRepositoryQD {
    List<ProductMaster> selectProductList(Long productId, int offset, int limit, String keyword);
}
