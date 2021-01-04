package com.pjh.web.shop.service;

import com.pjh.web.shop.controller.form.TradeForm;
import com.pjh.web.shop.domain.ProductMaster;
import com.pjh.web.shop.domain.ProductTrade;
import com.pjh.web.shop.domain.enumerate.OrderStatus;
import com.pjh.web.shop.repository.ProductMasterRepository;
import com.pjh.web.shop.repository.ProductTradeRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ProductOrderServiceTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    ProductOrderService productOrderService;
    @Autowired
    ProductTradeRepository productTradeRepository;
    @Autowired
    ProductMasterRepository productMasterRepository;

    public void productSetup(){
        productMasterRepository.deleteAll();
        ProductMaster productMaster = new ProductMaster();
        productMaster.setId(1L);
        productMaster.setName("테스트 상품");
        productMaster.setStock(5);
        productMaster.setPrice(15000);
        productMasterRepository.save(productMaster);
    }

    private static Stream<Arguments> setTradeForm(){

        return Stream.of(
                Arguments.of(1, 15000, 4),
                Arguments.of(2, 30000, 3),
                Arguments.of(3, 45000, 2),
                Arguments.of(4, 60000, 1),
                Arguments.of(5, 75000, 0)
        );
    }
    // 주문 정상 조회 테스트 실DB 연동
    @Disabled
    @ParameterizedTest
    @MethodSource("setTradeForm")
    public void productTradeTest(int orderCount, int totalPrice, int stock) {
//        productSetup();
        productMasterRepository.deleteAll();
        ProductMaster productMaster = new ProductMaster();
        productMaster.setId(1L);
        productMaster.setName("테스트 상품");
        productMaster.setStock(5);
        productMaster.setPrice(15000);
        productMasterRepository.save(productMaster);

        TradeForm tradeForm = new TradeForm();
        tradeForm.setOrderCount(orderCount);

        //when
        Long tradeId = productOrderService.trade(1L, tradeForm);
        Optional<ProductTrade> productTrade = productTradeRepository.findById(tradeId);
        Optional<ProductMaster> productMasterO = productMasterRepository.findById(1L);

        //then
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, productTrade.get().getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",orderCount, productTrade.get().getOrderLines().size());
        assertEquals("주문 가격은 가격 * 수량이다.", totalPrice, productTrade.get().getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",stock, productMasterO.get().getStock());
    }

    // 주문시 제품의 재고가 초과일때
    @Test
    public void productStockOverTest() {
        //given
        productSetup();
        int orderCount = 10;
        TradeForm tradeForm = new TradeForm();
        tradeForm.setOrderCount(orderCount);

        //when then
        assertThrows(RuntimeException.class, () -> productOrderService.trade(1L, tradeForm));
    }

    // 주문시 상품정보 찾을 수 없을 때
    @Test
    public void productNotfoundTest(){
        //given
        int orderCount = 2;
        TradeForm tradeForm = new TradeForm();
        tradeForm.setOrderCount(orderCount);
        //when then
        assertThrows(RuntimeException.class, () -> productOrderService.trade(1000L, tradeForm ));
    }
}