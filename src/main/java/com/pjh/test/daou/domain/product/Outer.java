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
public class Outer extends ProductMaster {
    private String material; //재질
}
