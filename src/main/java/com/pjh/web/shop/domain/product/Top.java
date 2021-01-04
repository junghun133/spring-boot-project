package com.pjh.web.shop.domain.product;


import com.pjh.web.shop.domain.ProductMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("T001")
@Setter
@Getter
public class Top extends ProductMaster implements Product{
    private String shoulderWidth; //어깨넓이

    @Override
    public void changeProductCreate(String name, int price, int stock, String explain, int deliveryFee) {
        setName(name);
        setPrice(price);
        setStock(stock);
        setExplain(explain);
        setDeliveryFee(deliveryFee);
    }
}
