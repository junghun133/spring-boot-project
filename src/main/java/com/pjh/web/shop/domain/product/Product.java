package com.pjh.web.shop.domain.product;

public interface Product {
    void changeProductCreate(String name, int price, int stock, String explain, int deliveryFee);
}
