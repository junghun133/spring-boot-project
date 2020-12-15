package com.pjh.test.daou.domain.product;


import com.pjh.test.daou.domain.ProductMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("O001")
@Setter
@Getter
public class Outer extends ProductMaster implements Product {
    private String material; //재질


    @Override
    public void changeProductCreate(String name, int price, int stock, String explain, int deliveryFee, String imagePath) {
        setName(name);
        setPrice(price);
        setStock(stock);
        setExplain(explain);
        setDeliveryFee(deliveryFee);
        setImagePath(imagePath);
    }
}
