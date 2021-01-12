package com.pjh.web.shop.repository.dsl;

import com.pjh.web.shop.domain.ProductMaster;

import java.util.List;

public interface ProductMasterRepositoryQD {
    List<ProductMaster> selectProductList(int offset, int limit, String keyword);
}
