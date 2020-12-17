package com.pjh.test.daou.service;

import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.product.Outer;
import com.pjh.test.daou.repository.ProductMasterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ProductMasterServiceTest {

    @Autowired
    ProductMasterRepository productMasterRepository;

    @Autowired
    ProductMasterService productMasterService;

    @BeforeEach
    public void setup(){
        productMasterRepository.deleteAll();
    }

    @Test
    public void findProductListExceptionTest(){
//        assertThrows(BadRequestProductException.class, () -> productMasterService.findProductsPaging(null));
    }

    @Test
    public void productCountTest(){
        //given
        ProductMaster product1 = createProduct("가죽자켓", 3000, 15000, "가죽", 1);
        ProductMaster product2 = createProduct("가디건", 3000, 10000, "면", 2);
        ProductMaster product3 = createProduct("패딩", 3000, 150000, "양털", 5);

        //when
        productMasterRepository.save(product1);
        productMasterRepository.save(product2);
        productMasterRepository.save(product3);

        //then
        assertEquals("상품의 갯수 조회 검증", productMasterService.findAllProductCount(), 3);
    }

    public ProductMaster createProduct(String name, int deliveryFee, int price, String material, int stock){
        Outer outer = new Outer();
        outer.setName(name);
        outer.setDeliveryFee(deliveryFee);
        outer.setPrice(price);
        outer.setMaterial(material);
        outer.setStock(stock);

        return outer;
    }

}