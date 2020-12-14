package com.pjh.test.daou.controller;

import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.product.ProductFactory;
import com.pjh.test.daou.domain.product.ProductType;
import com.pjh.test.daou.service.ProductMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        productMasterService.saveProduct(ProductFactory.createFormToProductObject(ProductType.valueOf(productForm.getProductType()), productForm, new ProductMaster()));

        return "redirect:/home";
    }

    @GetMapping("/product/list")
    public String productList(Model model){
        List<ProductMaster> products = productMasterService.findProducts();
        model.addAttribute("products", products);
        return "items/productList";
    }

    @GetMapping("/product/update/{productId}")
    public String updateProductForm(@PathVariable("productId") Long productId, Model model){
        ProductMaster product = productMasterService.findProduct(productId);

        ProductForm productForm = ProductFactory.createProductObjectToForm(ProductType.convertProductType(product.getProductType()), product);
        model.addAttribute("productForm", productForm);

//        productForm.setProductImage(); TODO
        return "items/updateProduct";
    }

    @PostMapping("/product/update/{productId}")
    public String updateProductForm(@ModelAttribute("productForm") ProductForm productForm){
//        productMasterService.saveProduct(ProductFactory.createFormToProductObject(ProductType.convertProductType(productForm.getProductType()), productForm));
        productMasterService.updateProduct(productForm);

        return "redirect:/home"; //요구사항에 명시되어있음 리스트로 가는것이 더좋아보임
    }

    @GetMapping("/product/delete/{productId}")
    public String deleteProductForm(@PathVariable Long productId){
        productMasterService.deleteProduct(productId);

        return "redirect:/home";
    }

    @GetMapping("/product/{productId}")
    public String detailProductForm(@PathVariable Long productId, Model model){
        ProductMaster product = productMasterService.findProduct(productId);
        model.addAttribute("product", product);
        model.addAttribute("tradeForm", new TradeForm());

        return "items/productDetail";
    }

    @PostMapping("/product/product/buy/{productId}")
    public String buyProductForm(@ModelAttribute("tradeForm") TradeForm tradeForm ){
//        productMasterService.buyProduct(tradeForm);
        return "redirect:/home";
    }
}
