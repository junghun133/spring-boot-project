package com.pjh.test.daou.service;

import com.pjh.test.daou.controller.form.TradeForm;
import com.pjh.test.daou.domain.*;
import com.pjh.test.daou.domain.enumerate.DeliveryStatus;
import com.pjh.test.daou.exception.NotFoundProductException;
import com.pjh.test.daou.http.entity.OrderRank;
import com.pjh.test.daou.repository.OrderLineRepository;
import com.pjh.test.daou.repository.ProductMasterRepository;
import com.pjh.test.daou.repository.ProductTradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductOrderService {
    private final ProductTradeRepository productTradeRepository;
    private final ProductMasterRepository productMasterRepository;
    private final OrderLineRepository orderLineRepository;

    // 상품 주문
    @Transactional
    public Long trade(Long productMasterId, TradeForm tradeForm){
        //상품 조회
        Optional<ProductMaster> productOptional = productMasterRepository.findById(productMasterId);
        productOptional.orElseThrow(NotFoundProductException::new);
        ProductMaster product = productOptional.get();

        //주문라인 생성
        OrderLine orderLine = OrderLine.createOrderLine(product, product.getPrice(), tradeForm.getOrderCount());

        //주문정보 생성
        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY);
        delivery.setRecipientInfo(new RecipientInfo(tradeForm.getRecipientName(), tradeForm.getRecipientPhone(), tradeForm.getRecipientAddress() ));

        //주문 생성
        ProductTrade productTrade = ProductTrade.createProductTrade(delivery, orderLine);
        productTrade.setOrderUserInfo(tradeForm.getOrderName(), tradeForm.getOrderPhone());

        //주문내역 저장 (cascade 로 인해 관련 entity 모두 save)
        productTradeRepository.save(productTrade);
        return productTrade.getId();
    }

    //검색
    public List<OrderLine> findAllOrder(){
        return orderLineRepository.findAll();
    }

    public List<OrderRank> findOrderRank(){
        List<OrderRank> orderRanks = orderLineRepository.select5CountGroupByProductMasterSumCount();
        if(orderRanks.size() > 0) {
            long totalOrderCount = orderLineRepository.sumByOrderCount();

            for (OrderRank orderRank : orderRanks) {
                orderRank.getPercent((int) totalOrderCount);
            }
        }
        return orderRanks;
    }
}
