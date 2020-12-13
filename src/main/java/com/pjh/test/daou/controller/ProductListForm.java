package com.pjh.test.daou.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductListForm {
    private Long productId;
    private String product;

    private int pageSize;
    public int getPageSize(){
        return pageSize == 0 ? 9 : pageSize;
    }
}
