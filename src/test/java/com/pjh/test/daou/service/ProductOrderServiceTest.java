package com.pjh.test.daou.service;

import com.pjh.test.daou.domain.Delivery;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.ProductTrade;
import com.pjh.test.daou.domain.RecipientInfo;
import com.pjh.test.daou.domain.enumerate.DeliveryStatus;
import com.pjh.test.daou.domain.enumerate.OrderStatus;
import com.pjh.test.daou.domain.product.Outer;
import com.pjh.test.daou.exception.NotEnoughStockException;
import com.pjh.test.daou.exception.NotFoundProductException;
import com.pjh.test.daou.repository.ProductTradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

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

    ProductMaster outer;
    Delivery delivery;
    int orderCount;

    @BeforeEach
    public void setup(){
        outer = createClothes("Cardigan", 15000, 5);
        delivery = createDelivery("박정훈", "01027075947", "경기도 용인시 수지구 죽전동 454");
        orderCount = 2;
    }

    /**
     * ================================================= 주문 정상 조회 테스트
     */
    @Test
    public void productTradeTest() {
        //given
        orderCount = 2;

        //when
        Long tradeId = productOrderService.trade(outer.getId(), delivery, orderCount);
        Optional<ProductTrade> productTrade = productTradeRepository.findById(tradeId);

        //then
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, productTrade.get().getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1, productTrade.get().getOrderLines().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 15000 * 2, productTrade.get().getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",3, outer.getStock());
    }

    /**
     * ================================================= 주문시 제품의 재고가 초과일때
     */
    @Test
    public void productStockOverTest() {
        //given
        orderCount = 10;
        //when then
        Long tradeId = productOrderService.trade(outer.getId(), delivery, orderCount);
        Optional<ProductTrade> productTrade = productTradeRepository.findById(tradeId);
        assertThrows(NotEnoughStockException.class, () -> productOrderService.trade(productTrade.get().getId(), delivery, orderCount));
    }

    /**
     * ================================================= 주문시 상품정보 찾을 수 없을 때
     */
    @Test
    public void productNotfoundTest(){
        //given
        orderCount = 2;
        //when then
        assertThrows(NotFoundProductException.class, () -> productOrderService.trade(1000L, delivery, orderCount));
    }

    private Outer createClothes(String name, int price, int stockQuantity) {
        Outer outer = new Outer();

        outer.setName(name);
        outer.setStock(stockQuantity);
        outer.setPrice(price);
        em.persist(outer);
        return outer;
    }

    private Delivery createDelivery(String name, String phone, String address){
        Delivery delivery = new Delivery();
        RecipientInfo recipientInfo = new RecipientInfo(name, phone, address);
        delivery.setRecipientInfo(recipientInfo);
        delivery.setStatus(DeliveryStatus.READY);
        return delivery;
    }
}