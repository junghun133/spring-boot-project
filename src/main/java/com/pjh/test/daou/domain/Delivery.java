package com.pjh.test.daou.domain;

import com.pjh.test.daou.domain.enumerate.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "TB_DELIVERY")
@SequenceGenerator(
        name = "DELIVERY_SEQ_GENERATOR",
        sequenceName = "DELIVERY_SEQ",
        initialValue = 1, allocationSize = 1)
public class Delivery {
    @Id @GeneratedValue(
            strategy=GenerationType.IDENTITY,
            generator="DELIVERY_SEQ"
    )
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
