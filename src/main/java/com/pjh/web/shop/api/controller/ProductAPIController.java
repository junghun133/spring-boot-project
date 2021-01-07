package com.pjh.web.shop.api.controller;

import com.pjh.web.shop.controller.HomeController;
import com.pjh.web.shop.domain.AttachmentImage;
import com.pjh.web.shop.domain.ProductMaster;
import com.pjh.web.shop.domain.product.Product;
import com.pjh.web.shop.http.entity.ProductResponse;
import com.pjh.web.shop.service.ProductMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController(value = "/api/v1/product")
@PreAuthorize("hasRole('ROLE_MANAGER')")
public class ProductAPIController {
    @Autowired
    ProductMasterService productMasterService;

    @RequestMapping(value = "/more/{pageNum}",
                    method = RequestMethod.GET)
    public EntityModel<ProductResponse> loadPage(@PathVariable int pageNum, @RequestHeader String authorization){
        PageRequest pageRequest = PageRequest.of(pageNum, 9 , Sort.by(Sort.Direction.DESC, "id"));
        ProductResponse productResponse = productMasterService.findProductsPageable(pageRequest);

        EntityModel<ProductResponse> response = new EntityModel<>(productResponse);

        WebMvcLinkBuilder wmlBuilder = linkTo(methodOn(this.getClass()).loadPage(pageNum+1, null));
        response.add(wmlBuilder.withRel("nextRequest"));

        return response;
    }

    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public List<ProductListAPIResponse> listAll(){
        List<ProductMaster> products = productMasterService.findProducts();

        return products.stream().map(ProductListAPIResponse::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/detail/{id}",
            method = RequestMethod.GET)
    public ProductListAPIResponse productDetail(@PathVariable int productId){
        ProductMaster product = productMasterService.findProduct((long) productId);
        return new ProductListAPIResponse(product);
    }


    static class ProductListAPIResponse{
        private Long id;
        private String name;
        private int price;
        private AttachmentResponse attachmentImage;
        private String explain;

        public ProductListAPIResponse(ProductMaster productMaster) {
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
