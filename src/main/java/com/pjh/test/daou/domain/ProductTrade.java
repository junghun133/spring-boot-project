package com.pjh.test.daou.domain;

import com.pjh.test.daou.domain.enumerate.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Table(name = "TB_PRODUCT_TRADE")
public class ProductTrade {
    @Id
    @GeneratedValue
    @Column(name = "product_trade_id")
    private Long id;

    @OneToMany(mappedBy = "productTrade")
    private final List<OrderLine> orderLines = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String orderName;
    private String orderPhone;

    @Builder(builderMethodName = "productTradeBuilder")
    public ProductTrade(Delivery delivery, LocalDateTime orderDate, OrderStatus status, String orderName, String orderPhone) {
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;
        this.orderName = orderName;
        this.orderPhone = orderPhone;
    }

    //주문정보 생성
    public static ProductTrade createProductTrade(Delivery delivery, OrderLine ... orderLines){
        ProductTrade productTrade = ProductTrade.productTradeBuilder()
                .delivery(delivery)
                .status(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .build();

        for (OrderLine orderLine : orderLines) {
            productTrade.addOrderLine(orderLine);
        }

        return productTrade;
    }

    public void addOrderLine(OrderLine orderLine){
        orderLines.add(orderLine);
        orderLine.setProductTrade(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setProductTrade(this);
    }

    /**
     * 전체 주문가격
     * @return int
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderLine orderLine : orderLines) {
            totalPrice += orderLine.getTotalPrice();
        }
        return totalPrice;
    }
}
