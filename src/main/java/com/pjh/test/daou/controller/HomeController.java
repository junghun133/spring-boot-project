package com.pjh.test.daou.controller;

import com.pjh.test.daou.controller.form.ProductListForm;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.service.ProductMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductMasterService productMasterService;

    @RequestMapping("/home")
    public String home(Model model){
        List<ProductMaster> productList = productMasterService.findProducts(new ProductListForm());
        model.addAttribute("productList", productList);

        return "home";
    }

    @PostMapping(value = "/home")
    public String homePost(Model model, ProductListForm productListForm){
        List<ProductMaster> productList = productMasterService.findProducts(productListForm);
        model.addAttribute("productList", productList);

        return "home";
    }

  /*  @PostMapping(value = "/home/more")
    public String homePostMore(Model model, ProductListForm productListForm){
        List<ProductMaster> productList = productMasterService.findProductsPaging(productListForm);
        model.addAttribute("productList", productList);
        int productSize = productMasterService.findAllProductCount();
        int totalPage = productSize / 9;
        if(productSize % 9 != 0)
            totalPage += 1;

        model.addAttribute("totalPage", totalPage);
        return "home::#boxes";
    }*/
  @RequestMapping("/image")
  public String image(Model model){

      return "imageTest";
  }
    @RequestMapping("/image/upload")
    public String image(Model model, @RequestParam("filess")  MultipartFile filess){
        System.out.println("file.getOriginalFilename() = " + filess.getOriginalFilename());
        return "imageTest";
    }
}
