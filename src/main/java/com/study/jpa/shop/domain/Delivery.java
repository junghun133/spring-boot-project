package com.study.jpa.shop.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery")
    private Order order;
}
