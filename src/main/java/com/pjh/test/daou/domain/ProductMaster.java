package com.pjh.test.daou.domain;

import com.pjh.test.daou.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type") // single table 구분
@Getter @Setter
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
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "productMaster", cascade = CascadeType.ALL)
    private List<ProductModifyHistory> productModifyHistory;

    @Lob
    private String explain;

    public void deductStock(int orderStock) {
        int restStock = this.stock - orderStock;
        if(restStock < 0) {
            log.info("============== Stock deduction failed =========== ");
            log.info("id: " + id);
            log.info("name: " + name);
            throw new NotEnoughStockException("need more stock");
        }

        this.stock = restStock;
    }
}
