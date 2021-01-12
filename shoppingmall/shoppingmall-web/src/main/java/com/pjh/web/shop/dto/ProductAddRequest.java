package com.pjh.web.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductAddRequest {
    private Long id;

    private String name;
    private int price;
    private int stock;
    private int deliveryFee;
    private String explain;
    private String productType;
    private Long attachmentImageId;
}
