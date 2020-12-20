package com.pjh.test.daou.controller;

import com.pjh.test.daou.domain.OrderLine;
import com.pjh.test.daou.domain.ProductTrade;
import com.pjh.test.daou.http.entity.OrderRank;
import com.pjh.test.daou.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final ProductOrderService productOrderService;

    @GetMapping("/orders")
    public String orderList(Model model){
        List<OrderLine> allOrder = productOrderService.findAllOrder();
        List<OrderRank> orderRank = productOrderService.findOrderRank();


        model.addAttribute("allOrder", allOrder);
        model.addAttribute("orderRank", orderRank);

        return "items/tradeList";
    }
}
