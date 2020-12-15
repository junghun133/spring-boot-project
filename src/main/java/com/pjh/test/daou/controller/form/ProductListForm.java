package com.pjh.test.daou.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductListForm {
    private Long productId;
    private String product;

    int page;
}
