package com.pjh.test.daou.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "TB_PRODUCT_MODIFY_HIST")
public class ProductModifyHistory {
    @Id
    @GeneratedValue
    @Column(name = "product_modify_hist_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_master_id")
    private ProductMaster productMaster;

    private LocalDateTime modifiedDate;
    @Lob
    private String modifiedLog;
}
