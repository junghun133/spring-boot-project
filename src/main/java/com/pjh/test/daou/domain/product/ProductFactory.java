package com.pjh.test.daou.domain.product;

import com.pjh.test.daou.controller.ProductForm;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.exception.NotProvideProductTypeExcpetion;

import java.time.LocalDateTime;

public class ProductFactory {

    public static ProductMaster createProduct(ProductType productType, ProductForm productForm){
        switch (productType){
            case Outer:
                Outer outer = new Outer();
                outer.setMaterial(productForm.getMaterial());

                outer.setName(productForm.getName());
                outer.setExplain(productForm.getExplain());
                outer.setPrice(productForm.getPrice());
                outer.setStock(productForm.getStock());
                outer.setDeliveryFee(productForm.getDeliveryFee());
                outer.setRegistrationDate(LocalDateTime.now());
                return outer;
            case Pants:
                Pants pants = new Pants();
                pants.setWaistWidth(productForm.getWaistWidth());

                pants.setName(productForm.getName());
                pants.setExplain(productForm.getExplain());
                pants.setPrice(productForm.getPrice());
                pants.setStock(productForm.getStock());
                pants.setDeliveryFee(productForm.getDeliveryFee());
                pants.setRegistrationDate(LocalDateTime.now());
                return pants;
            case Top:
                Top top = new Top();
                top.setShoulderWidth(productForm.getShoulderWidth());

                top.setName(productForm.getName());
                top.setExplain(productForm.getExplain());
                top.setPrice(productForm.getPrice());
                top.setStock(productForm.getStock());
                top.setDeliveryFee(productForm.getDeliveryFee());
                top.setRegistrationDate(LocalDateTime.now());
                return top;

            default:
                throw new NotProvideProductTypeExcpetion();
        }
    }
}
