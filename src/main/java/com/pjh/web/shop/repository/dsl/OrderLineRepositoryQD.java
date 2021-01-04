package com.pjh.web.shop.repository.dsl;

import com.pjh.web.shop.http.entity.OrderRank;

import java.util.List;

public interface OrderLineRepositoryQD {
    List<OrderRank> select5CountGroupByProductMasterSumCount();
    Integer sumByOrderCount();
}
