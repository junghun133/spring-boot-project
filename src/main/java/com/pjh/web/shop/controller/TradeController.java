package com.pjh.web.shop.controller;

import com.pjh.web.shop.controller.form.TradeForm;
import com.pjh.web.shop.domain.ProductMaster;
import com.pjh.web.shop.service.ProductMasterService;
import com.pjh.web.shop.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class TradeController {
    private final ProductOrderService productOrderService;
    private final ProductMasterService productMasterService;

    @GetMapping("/product/{productId}")
    public String detailProductForm(@PathVariable Long productId, Model model){
        ProductMaster product = productMasterService.findProduct(productId);
        model.addAttribute("product", product);
        model.addAttribute("tradeForm", new TradeForm());

        return "items/productDetail";
    }



    @PostMapping("/product/buy")
    public String buyProduct(@Valid TradeForm tradeForm, @RequestParam("productId") Long productId, BindingResult br) {
        if(br.hasErrors()){
            return "items/productDetail";
        }
        productOrderService.trade(productId, tradeForm);

        return "redirect:/home";
    }

}
