package com.pjh.test.daou.domain.product;


import com.pjh.test.daou.domain.ProductMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("T001")
@Setter
@Getter
public class Top extends ProductMaster {
    private String shoulderWidth; //어깨넓이
}
