package com.pjh.test.daou.domain.product;

import com.pjh.test.daou.controller.ProductForm;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.exception.NotProvideProductTypeExcpetion;

public class ProductFactory {

    public static ProductMaster createFormToProductObject(ProductType productType, ProductForm productForm){

        switch (productType){
            case Outer:
                Outer outer = new Outer();
                outer.setMaterial(productForm.getMaterial());

                outer.changeProductCreate(
                        productForm.getName(),
                        productForm.getPrice(),
                        productForm.getStock(),
                        productForm.getExplain(),
                        productForm.getDeliveryFee()
                );
                return outer;
            case Pants:
                Pants pants = new Pants();
                pants.setWaistWidth(productForm.getWaistWidth());

                pants.changeProductCreate(
                        productForm.getName(),
                        productForm.getPrice(),
                        productForm.getStock(),
                        productForm.getExplain(),
                        productForm.getDeliveryFee()
                );
                return pants;
            case Top:
                Top top = new Top();
                top.setShoulderWidth(productForm.getShoulderWidth());

                top.changeProductCreate(
                        productForm.getName(),
                        productForm.getPrice(),
                        productForm.getStock(),
                        productForm.getExplain(),
                        productForm.getDeliveryFee()
                );
                return top;

            default:
                throw new NotProvideProductTypeExcpetion();
        }
    }

    public static ProductMaster updateFormToProductObject(ProductType productType, ProductForm productForm, ProductMaster productMaster){

        switch (productType){
            case Outer:
                Outer outer = (Outer) productMaster;
                outer.setMaterial(productForm.getMaterial());

                outer.changeProductCreate(
                        productForm.getName(),
                        productForm.getPrice(),
                        productForm.getStock(),
                        productForm.getExplain(),
                        productForm.getDeliveryFee()
                );
                return outer;
            case Pants:
                Pants pants = (Pants) productMaster;
                pants.setWaistWidth(productForm.getWaistWidth());

                pants.changeProductCreate(
                        productForm.getName(),
                        productForm.getPrice(),
                        productForm.getStock(),
                        productForm.getExplain(),
                        productForm.getDeliveryFee()
                );
                return pants;
            case Top:
                Top top = (Top) productMaster;
                top.setShoulderWidth(productForm.getShoulderWidth());

                top.changeProductCreate(
                        productForm.getName(),
                        productForm.getPrice(),
                        productForm.getStock(),
                        productForm.getExplain(),
                        productForm.getDeliveryFee()
                );
                return top;

            default:
                throw new NotProvideProductTypeExcpetion();
        }
    }
    public static ProductForm createProductObjectToForm(ProductType productType, ProductMaster productMaster){
        ProductForm productForm = new ProductForm();
        switch (productType){
            case Outer:
                Outer outer = (Outer) productMaster;
                productForm.setId(outer.getId());
                productForm.setMaterial(outer.getMaterial());

                productForm.setName(outer.getName());
                productForm.setExplain(outer.getExplain());
                productForm.setPrice(outer.getPrice());
                productForm.setStock(outer.getStock());
                productForm.setDeliveryFee(outer.getDeliveryFee());

                productForm.setProductType(outer.getProductType());
                return productForm;
            case Pants:
                Pants pants = (Pants) productMaster;
                productForm.setId(pants.getId());
                productForm.setWaistWidth(pants.getWaistWidth());

                productForm.setName(pants.getName());
                productForm.setExplain(pants.getExplain());
                productForm.setPrice(pants.getPrice());
                productForm.setStock(pants.getStock());
                productForm.setDeliveryFee(pants.getDeliveryFee());

                productForm.setProductType(pants.getProductType());
                return productForm;
            case Top:
                Top top = (Top) productMaster;
                productForm.setId(top.getId());
                productForm.setShoulderWidth(top.getShoulderWidth());

                productForm.setName(top.getName());
                productForm.setExplain(top.getExplain());
                productForm.setPrice(top.getPrice());
                productForm.setStock(top.getStock());
                productForm.setDeliveryFee(top.getDeliveryFee());

                productForm.setProductType(top.getProductType());
                return productForm;
            default:
                throw new NotProvideProductTypeExcpetion();
        }
    }


}
