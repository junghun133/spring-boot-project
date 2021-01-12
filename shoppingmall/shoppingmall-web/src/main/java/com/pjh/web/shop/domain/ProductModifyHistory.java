package com.pjh.web.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "TB_PRODUCT_MODIFY_HIST")
@SequenceGenerator(
        name = "PRODUCT_MODIFY_SEQ_GENERATOR",
        sequenceName = "PRODUCT_MODIFY_SEQ",
        initialValue = 1, allocationSize = 1)
public class ProductModifyHistory {
    @Id @GeneratedValue(
            strategy=GenerationType.IDENTITY,
            generator="PRODUCT_MODIFY_SEQ"
    )
    @Column(name = "product_modify_hist_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_master_id")
    private ProductMaster productMaster;

    private LocalDateTime modifiedDate;

    @Lob
    private String modifiedLog;
}
