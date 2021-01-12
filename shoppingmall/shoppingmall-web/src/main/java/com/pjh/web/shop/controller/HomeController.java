package com.pjh.web.shop.controller;

import com.pjh.web.shop.controller.form.ProductListForm;
import com.pjh.web.shop.domain.AttachmentImage;
import com.pjh.web.shop.domain.ProductMaster;
import com.pjh.web.shop.service.ProductMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    ProductMasterService productMasterService;

    @GetMapping("/")
    public String landing(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model){
        List<ProductMaster> findResult = productMasterService.findProductsWithKeyword(null);
        List<ProductListResponse> productList = findResult.stream()
                .map(ProductListResponse::new).collect(Collectors.toList());

        model.addAttribute("productList", findResult);

        return "home";
    }

    @PostMapping(value = "/home")
    public String homePost(Model model, ProductListForm productListForm){
        List<ProductMaster> findResult = productMasterService.findProductsWithKeyword(productListForm.getProduct());
        List<ProductListResponse> productList = findResult.stream()
                .map(ProductListResponse::new).collect(Collectors.toList());
        model.addAttribute("productList", productList);

        return "home";
    }

    static class ProductListResponse{
        private Long id;
        private String name;
        private int price;
        private AttachmentResponse attachmentImage;
        private String explain;

        public ProductListResponse(ProductMaster productMaster) {
            id = productMaster.getId();
            name = productMaster.getName();
            price = productMaster.getPrice();
            attachmentImage = new AttachmentResponse(productMaster.getAttachmentImage());
            explain = productMaster.getExplain();
        }
    }

    static class AttachmentResponse{
        private String imagePath;

        public AttachmentResponse(AttachmentImage attachmentImage) {
            imagePath = attachmentImage.getImagePath();
        }
    }
}
