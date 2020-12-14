package com.pjh.test.daou.controller;

import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.product.ProductFactory;
import com.pjh.test.daou.domain.product.ProductType;
import com.pjh.test.daou.service.ProductMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductMasterService productMasterService;

    @RequestMapping("/product")
    public String manager(){
        return "product";
    }

    @GetMapping("/product/add")
    public String createForm(Model model){
        model.addAttribute("productForm", new ProductForm());
        return "items/addProduct";
    }

    @PostMapping("/product/add")
    public String createProduct(ProductForm productForm){
//        productMaster.setImagePath(productForm.getImagePath()); TODO 이미지 등록
        productMasterService.saveProduct(ProductFactory.createProduct(ProductType.valueOf(productForm.getProductType()), productForm));

        return "redirect:/home";
    }

    @GetMapping("/product/list")
    public String productList(Model model){
        List<ProductMaster> products = productMasterService.findProducts();
        model.addAttribute("products", products);
        return "items/productList";

    }
}
