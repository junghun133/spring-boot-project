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
/*
==================== 다대일 =======================
외래키있는 곳
@ManyToOne, @JoinColumn 하면 됌

/*
===================== 연관관계 주인 ========================
테이블은 외래키 하나로 두 테이블이 연관관계를 맺음

객체 양방향 관계는 A->B, B->A 처럼 참조가 2군데

객체 양방향 관계는 참조가 2군데 있음. 둘 중 테이블의 외래키를 관리할 곳을 지정해야함(mappedBy)

연관관계의 주인: 외래키를 관리하는 참조
 */

/*
====================
 */