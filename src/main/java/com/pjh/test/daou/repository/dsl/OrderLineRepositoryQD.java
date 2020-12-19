package com.pjh.test.daou.repository.dsl;

import com.pjh.test.daou.http.entity.OrderRank;

import java.util.List;

public interface OrderLineRepositoryQD {
    List<OrderRank> select5CountGroupByProductMasterSumCount();
    Integer sumByOrderCount();
}
