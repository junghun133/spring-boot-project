package com.pjh.web.shop.controller;

import com.pjh.web.shop.controller.form.ProductForm;
import com.pjh.web.shop.domain.ProductMaster;
import com.pjh.web.shop.domain.ProductModifyHistory;
import com.pjh.web.shop.domain.product.ProductFactory;
import com.pjh.web.shop.domain.product.ProductType;
import com.pjh.web.shop.exception.BadRequestProductException;
import com.pjh.web.shop.exception.InternalServerException;
import com.pjh.web.shop.exception.NotProvideProductTypeExcpetion;
import com.pjh.web.shop.service.ProductHistoryService;
import com.pjh.web.shop.service.ProductImageService;
import com.pjh.web.shop.service.ProductMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ProductController {

    private final ProductMasterService productMasterService;
    private final ProductHistoryService productHistoryService;
    private final ProductImageService productImageService;

    @RequestMapping("/manager/product")
    public String manager(){
        return "product";
    }

    @GetMapping("/manager/product/add")
    public String createProduct(Model model){
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("attachments", productImageService.getImageList());
        return "items/addProduct";
    }

    @PostMapping("/manager/product/add")
    public String createProduct(ProductForm productForm) throws NotProvideProductTypeExcpetion {
        if(productForm.getImageId() == null)
            return "redirect:/product/add";

        productMasterService.saveProduct(ProductFactory.createFormToProductObject(
                ProductType.convertProductType(productForm.getProductType()),
                productForm
                )
                ,productForm.getImageId()
        );

        return "redirect:/manager/product";
    }

    @GetMapping("/manager/product/add/{imageId}")
    public String createProductChooseImage(@PathVariable Long imageId, Model model){
        model.addAttribute("selectedImageId", imageId);
        model.addAttribute("productForm", new ProductForm());

        return "items/addProduct";
    }

    @GetMapping("/manager/product/list")
    public String productList(Model model) throws BadRequestProductException {
        List<ProductMaster> products = productMasterService.findProducts();
        model.addAttribute("products", products);
        return "items/productList";
    }

    @GetMapping("/manager/product/update/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId, Model model){
        ProductMaster product = productMasterService.findProduct(productId);

        ProductForm productForm = ProductFactory.createProductObjectToForm(ProductType.convertProductType(product.getProductType()), product);
        model.addAttribute("productForm", productForm);

//        productForm.setProductImage(); TODO
        return "items/updateProduct";
    }

    @PostMapping("/manager/product/update/{productId}")
    public String updateProduct(@ModelAttribute("productForm") ProductForm productForm) throws BadRequestProductException, InternalServerException {
//        productMasterService.saveProduct(ProductFactory.createFormToProductObject(ProductType.convertProductType(productForm.getProductType()), productForm));
        productMasterService.updateProduct(productForm);

        return "redirect:/manager/product"; //요구사항에 명시되어있음 하지만 리스트로 가는것이 더좋아보임
    }

    @GetMapping("/manager/product/delete/{productId}")
    public String deleteProductForm(@PathVariable Long productId){
        productMasterService.deleteProduct(productId);

        return "redirect:/manager/product";
    }

    @GetMapping("/manager/product/history")
    public String HistoryList(Model model){
        List<ProductModifyHistory> histories = productHistoryService.findHistories();
        model.addAttribute("histories", histories);

        return "items/updateHistory";
    }
}
