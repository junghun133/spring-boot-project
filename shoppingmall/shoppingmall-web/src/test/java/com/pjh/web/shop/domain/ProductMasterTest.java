package com.pjh.web.shop.domain;

import com.pjh.web.shop.exception.NotEnoughStockException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductMasterTest {
    ProductMaster productMaster;

    @Test
    public void deductStockSuccessTest(){
        productMaster = new ProductMaster();
        productMaster.setStock(5);

        productMaster.deductStock(2);
        assertEquals("정상 재고 차감 val: 2", productMaster.getStock(), 3);
    }

    @Test
    public void deductStockZeroTest(){
        productMaster = new ProductMaster();
        productMaster.setStock(5);

        productMaster.deductStock(5);
        assertEquals("정상 재고 차감 val: 5", productMaster.getStock(), 0);
    }

    @Test
    public void deductStockNothingHPTest(){
        productMaster = new ProductMaster();
        productMaster.setStock(5);

        productMaster.deductStock(0);
        assertEquals("정상 재고 차감 val: 0", productMaster.getStock(), 5);
    }

    @Test
    public void 재고차감실패_테스트(){
        productMaster = new ProductMaster();
        productMaster.setStock(5);

        assertThrows(NotEnoughStockException.class, () -> productMaster.deductStock(6));
    }
}