package com.pjh.test.daou.domain;

import com.pjh.test.daou.exception.NotEnothStockException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type") // single table 구분
@Getter
@Table(name = "TB_PRODUCT_MASTER")
public class ProductMaster {

    @Id
    @GeneratedValue
    @Column(name = "product_master_id")
    private Long id;

    private String name;
    private int price;
    private int stock;
    private String imagePath;
    private int deliveryFee;

    @OneToMany(mappedBy = "productMaster")
    private List<ProductModifyHistory> productModifyHistory;

    @Lob
    private String explain;

    public void deductStock(int orderStock) {
        int restStock = this.stock - orderStock;
        if(restStock < 0) {
            log.info("============== Stock deduction failed =========== ");
            log.info("id: " + id);
            log.info("name: " + name);
            throw new NotEnothStockException("need more stock");
        }

        this.stock = restStock;
    }
}
