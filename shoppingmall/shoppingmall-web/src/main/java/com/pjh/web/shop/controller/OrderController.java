package com.pjh.web.shop.controller;

import com.pjh.web.shop.domain.Delivery;
import com.pjh.web.shop.domain.OrderLine;
import com.pjh.web.shop.domain.ProductMaster;
import com.pjh.web.shop.domain.ProductTrade;
import com.pjh.web.shop.domain.enumerate.DeliveryStatus;
import com.pjh.web.shop.http.entity.OrderRank;
import com.pjh.web.shop.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final ProductOrderService productOrderService;

    @GetMapping("/manager/orders")
    public String orderList(Model model){
        List<OrderLine> allOrder = productOrderService.findAllOrder();
        List<OrderRank> orderRank = productOrderService.findOrderRank();
        List<OrderLineResponse> orderLineResult = allOrder.stream().map(OrderLineResponse::new).collect(Collectors.toList());


        model.addAttribute("orderLineResult", orderLineResult);
        model.addAttribute("orderRank", orderRank);

        return "items/tradeList";
    }

    static class OrderLineResponse{
        ProductTradeResponse productTrade;
        ProductMasterResponse productMaster;
        int count;
        int orderPrice;
        int totalPrice;

        public OrderLineResponse(OrderLine orderLine) {
            productTrade = new ProductTradeResponse(orderLine.getProductTrade());
            productMaster = new ProductMasterResponse(orderLine.getProductMaster());
            count = orderLine.getCount();
            orderPrice = orderLine.getOrderPrice();
            totalPrice = orderLine.getTotalPrice();
        }
    }

    static class ProductTradeResponse{
        Long id;
        String orderName;
        String orderPhone;
        DeliveryResponse deliveryResponse;
        LocalDateTime orderDate;

        public ProductTradeResponse(ProductTrade productTrade) {
            id = productTrade.getId();
            orderName = productTrade.getOrderName();
            orderPhone = productTrade.getOrderPhone();
            deliveryResponse = new DeliveryResponse(productTrade.getDelivery());
            orderDate = productTrade.getOrderDate();
        }
    }

    static class ProductMasterResponse{
        Long id;
        String name;
        public ProductMasterResponse(ProductMaster productMaster) {
            id = productMaster.getId();
            name = productMaster.getName();
        }
    }

    static class DeliveryResponse {
        DeliveryStatus status;
        public DeliveryResponse(Delivery delivery) {
            status = delivery.getStatus();
        }
    }
}
