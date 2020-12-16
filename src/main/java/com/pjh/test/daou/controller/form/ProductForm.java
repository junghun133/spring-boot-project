package com.pjh.test.daou.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {
    private Long id;
    private String name;
    private int price;
    private int stock;
    private String explain;

    private int deliveryFee;

    private Long imageId;
    private String productType;

    private String material; //outer 속성
    private String waistWidth; //pants 속성
    private String shoulderWidth; //top 속성
}
