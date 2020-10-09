package com.study.jpa.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
public class ShopMember {
    @Id
    @GeneratedValue
    @Column(name = "SHOP_MEMBER_ID")
    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "shopMember")
    private List<Order> orders = new ArrayList<>();
}
