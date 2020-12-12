package com.pjh.test.daou.service;

import com.pjh.test.daou.domain.Delivery;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.ProductTrade;
import com.pjh.test.daou.domain.RecipientInfo;
import com.pjh.test.daou.domain.enumerate.DeliveryStatus;
import com.pjh.test.daou.domain.enumerate.OrderStatus;
import com.pjh.test.daou.domain.product.Outer;
import com.pjh.test.daou.repository.ProductTradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

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

    @Test
    public void productTrade() {
        //given
        ProductMaster outer = createClothes("Cardigan", 15000, 5);
        Delivery delivery = createDelivery("박정훈", "01027075947", "경기도 용인시 수지구 죽전동 454");
        int orderCount = 2;

        //when
        Long tradeId = productOrderService.trade(outer.getId(), delivery, orderCount);

        //then
        Optional<ProductTrade> productTrade = productTradeRepository.findById(tradeId);
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, productTrade.get().getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1, productTrade.get().getOrderLines().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 15000 * 2, productTrade.get().getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",3, outer.getStock());
    }

    @Test
    public void productStockOver() {
        //given

        //when

        //then
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