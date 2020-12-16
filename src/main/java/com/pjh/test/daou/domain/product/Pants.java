package com.pjh.test.daou.domain.product;

import com.pjh.test.daou.domain.ProductMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("P001")
@Setter
@Getter
public class Pants extends ProductMaster implements Product{
    private String waistWidth; //허리둘레

    @Override
    public void changeProductCreate(String name, int price, int stock, String explain, int deliveryFee) {
        setName(name);
        setPrice(price);
        setStock(stock);
        setExplain(explain);
        setDeliveryFee(deliveryFee);
//        setImagePath(imagePath);
    }
}
