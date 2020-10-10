package com.study.jpa.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Setter @Getter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SHOP_MEMBER_ID")
    private ShopMember shopMember;

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    /////1:1 관계
    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;
}
