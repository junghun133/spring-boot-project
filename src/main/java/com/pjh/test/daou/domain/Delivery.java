package com.pjh.test.daou.domain;

import com.pjh.test.daou.domain.enumerate.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "TB_DELIVERY")
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Setter
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private ProductTrade productTrade;

    @Embedded
    private RecipientInfo recipientInfo;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
