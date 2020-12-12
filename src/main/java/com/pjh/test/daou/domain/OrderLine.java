package com.pjh.test.daou.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_ORDER_LINE")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //일반생성 제한
public class OrderLine {
    @Id
    @GeneratedValue
    @Column(name = "order_line_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_trade_id")
    private ProductTrade productTrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_master_id")
    private ProductMaster productMaster;
    private int orderPrice;
    private int count;

    @Builder(builderMethodName = "orderLineBuilder")
    public OrderLine(ProductTrade productTrade, ProductMaster productMaster, int orderPrice, int count) {
        this.productTrade = productTrade;
        this.productMaster = productMaster;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderLine createOrderLine(ProductMaster productMaster, int orderPrice, int count){
        OrderLine orderLine = OrderLine.orderLineBuilder()
                .productMaster(productMaster)
                .orderPrice(orderPrice)
                .count(count)
                .build();

        productMaster.deductStock(count);
        return orderLine;
    }

    public int getTotalPrice() {
        return getOrderPrice() * count;
    }
}
