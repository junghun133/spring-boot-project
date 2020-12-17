package com.pjh.test.daou.controller;

import com.pjh.test.daou.controller.form.ProductListForm;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.service.ProductMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductMasterService productMasterService;

    @RequestMapping("/home")
    public String home(Model model){
        List<ProductMaster> productList = productMasterService.findProductsWithKeyword(null);
        model.addAttribute("productList", productList);

        return "home";
    }

    @PostMapping(value = "/home")
    public String homePost(Model model, ProductListForm productListForm){
        List<ProductMaster> productList = productMasterService.findProductsWithKeyword(productListForm.getProduct());
        model.addAttribute("productList", productList);

        return "home";
    }
}
