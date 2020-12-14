package com.pjh.test.daou.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductForm {
    private Long id;
    private String name;
    private int price;
    private int stock;
    private String explain;

    private MultipartFile productImage;
    private int deliveryFee;

    private String productType;

    private String material; //outer 속성 -재질
    private String waistWidth; //pants 속성 - 허리둘레
    private String shoulderWidth; //top 속성 - 어깨넓이
}
