package com.pjh.test.daou.controller;

import com.pjh.test.daou.service.ProductMasterService;
import com.pjh.test.daou.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TradeController {
    private final ProductOrderService productOrderService;
    private final ProductMasterService productMasterService;


}
