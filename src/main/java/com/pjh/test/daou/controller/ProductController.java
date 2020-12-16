package com.pjh.test.daou.controller;

import com.pjh.test.daou.controller.form.ProductForm;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.ProductModifyHistory;
import com.pjh.test.daou.domain.product.ProductFactory;
import com.pjh.test.daou.domain.product.ProductType;
import com.pjh.test.daou.service.ProductHistoryService;
import com.pjh.test.daou.service.ProductImageService;
import com.pjh.test.daou.service.ProductMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductMasterService productMasterService;
    private final ProductHistoryService productHistoryService;
    private final ProductImageService productImageService;

    @RequestMapping("/product")
    public String manager(){
        return "product";
    }

    @GetMapping("/product/add")
    public String createProduct(Model model){
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("attachments", productImageService.getImageList());
        return "items/addProduct";
    }

    @PostMapping("/product/add")
    public String createProduct(ProductForm productForm){
        if(productForm.getImageId() == null)
            return "redirect:/product/add";

        productMasterService.saveProduct(ProductFactory.createFormToProductObject(
                ProductType.convertProductType(productForm.getProductType()),
                productForm
                ), productForm.getImageId()
        );

        return "redirect:/home";
    }

    @GetMapping("/product/add/{imageId}")
    public String createProductChooseImage(@PathVariable Long imageId, Model model){
        model.addAttribute("selectedImageId", imageId);
        model.addAttribute("productForm", new ProductForm());

        return "items/addProduct";
    }

    @GetMapping("/product/list")
    public String productList(Model model){
        List<ProductMaster> products = productMasterService.findProducts();
        model.addAttribute("products", products);
        return "items/productList";
    }

    @GetMapping("/product/update/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId, Model model){
        ProductMaster product = productMasterService.findProduct(productId);

        ProductForm productForm = ProductFactory.createProductObjectToForm(ProductType.convertProductType(product.getProductType()), product);
        model.addAttribute("productForm", productForm);

//        productForm.setProductImage(); TODO
        return "items/updateProduct";
    }

    @PostMapping("/product/update/{productId}")
    public String updateProduct(@ModelAttribute("productForm") ProductForm productForm){
//        productMasterService.saveProduct(ProductFactory.createFormToProductObject(ProductType.convertProductType(productForm.getProductType()), productForm));
        productMasterService.updateProduct(productForm);

        return "redirect:/home"; //요구사항에 명시되어있음 하지만 리스트로 가는것이 더좋아보임
    }

    @GetMapping("/product/delete/{productId}")
    public String deleteProductForm(@PathVariable Long productId){
        productMasterService.deleteProduct(productId);

        return "redirect:/home";
    }

    @GetMapping("/product/history")
    public String HistoryList(Model model){
        List<ProductModifyHistory> histories = productHistoryService.findHistories();
        model.addAttribute("histories", histories);

        return "items/updateHistory";
    }
}
