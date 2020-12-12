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
public class Pants extends ProductMaster {
    private String waistWidth; //허리둘레
}
