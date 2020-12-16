package com.pjh.test.daou.domain;

import com.pjh.test.daou.domain.product.Product;
import com.pjh.test.daou.exception.InternalServerException;
import com.pjh.test.daou.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type") // single table 구분
@Getter @Setter
@Table(name = "TB_PRODUCT_MASTER")
@SequenceGenerator(
        name = "PRODUCT_MASTER_SEQ_GENERATOR",
        sequenceName = "PRODUCT_MASTER_SEQ",
        initialValue = 31, allocationSize = 1)
public class ProductMaster implements Cloneable{
    @Id @GeneratedValue(
            strategy=GenerationType.IDENTITY,
            generator="PRODUCT_MASTER_SEQ"
    )
    @Column(name = "product_master_id")
    private Long id;

    private String name;
    private int price;
    private int stock;
    private int deliveryFee;
    private LocalDateTime registrationDate;
    @Lob
    private String explain;
    @Column(name = "product_type", insertable = false, updatable = false)
    private String productType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "att_img_id")
    private AttachmentImage attachmentImage;

    @OneToMany(mappedBy = "productMaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductModifyHistory> productModifyHistoryList = new ArrayList<>();



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

    public void addModifyHistory(ProductModifyHistory productModifyHistory){
        productModifyHistoryList.add(productModifyHistory);
        productModifyHistory.setProductMaster(this);
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalServerException(e);
        }
    }
}
