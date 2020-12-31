package com.pjh.test.daou.controller;

import com.pjh.test.daou.service.ProductMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
