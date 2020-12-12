package com.pjh.test.daou.service;

import com.pjh.test.daou.domain.Delivery;
import com.pjh.test.daou.domain.OrderLine;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.ProductTrade;
import com.pjh.test.daou.repository.ProductMasterRepository;
import com.pjh.test.daou.repository.ProductTradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductOrderService {
    private final ProductTradeRepository productTradeRepository;
    private final ProductMasterRepository productMasterRepository;

    /**
     * 주문
     * @param productMasterId
     * @param delivery
     * @param count
     * @return
     */
    @Transactional
    public Long trade(Long productMasterId, Delivery delivery, int count){
        //상품 조회
        Optional<ProductMaster> productOptional = productMasterRepository.findById(productMasterId);
        productOptional.orElseThrow(NoSuchElementException::new);

        ProductMaster product = productOptional.get();

        //주문라인 생성
        OrderLine orderLine = OrderLine.createOrderLine(product, product.getPrice(), count);

        //주문 생성
        ProductTrade productTrade = ProductTrade.createProductTrade(delivery, orderLine);

        productTradeRepository.save(productTrade);
        return productTrade.getId();
    }

    //검색
//    public List<ProductMaster> findTrade(ProductSearch productSearch){
//        productMasterRepository.findALL(productSearch);
//    }
}
