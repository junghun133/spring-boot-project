package com.pjh.test.daou.domain.product;

import com.pjh.test.daou.exception.TypeConvertException;

public enum ProductType {
    Outer("O"), //아우터
    Pants("P"), //하의
    Top("T"), //상의
    ETC("E"); //기타

    private String value;

    ProductType(String  value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static ProductType convertProductType(String modelNumber){
        if(modelNumber == null)
            return ETC;

        String modelType = modelNumber.substring(0, 1);
        for(ProductType p : ProductType.values()){
            if(p.value.contentEquals(modelType)){
                return p;
            }
        }
        throw new TypeConvertException();
    }
}
