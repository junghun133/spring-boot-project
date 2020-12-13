package com.pjh.test.daou.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductListForm {
    private Long productId;
    private String product;

    private int pageSize;
    public int getPageSize(){
        return pageSize == 0 ? 9 : pageSize;
    }
}
