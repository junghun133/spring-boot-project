package com.pjh.web.shop.controller;

import com.pjh.web.shop.service.ProductMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LandingController {
    @Autowired
    ProductMasterService service;

   /* @RequestMapping("/")
    public String landing(Model model){
        int allProductCount = service.findAllProductCount();
        model.addAttribute("allProductCount", allProductCount);
        return "landing";
    }*/
}
