package com.pjh.test.daou.controller.api;

import com.pjh.test.daou.http.entity.ProductResponse;
import com.pjh.test.daou.service.ProductMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController(value = "/api/v1/product")
@PreAuthorize("hasRole('ROLE_MANAGER')")
public class ProductDataController {
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


    @RequestMapping(value = "/list/all",
            method = RequestMethod.GET)
    public EntityModel<ProductResponse> listAll(@RequestHeader String authorization){
//        EntityModel<ProductResponse> response = new EntityModel<>(productResponse);
//        WebMvcLinkBuilder wmlBuilder = linkTo(methodOn(this.getClass()).loadPage(pageNum+1));
//        response.add(wmlBuilder.withRel("nextRequest"));

        return null;
    }

    @RequestMapping(value = "/detail/{id}",
            method = RequestMethod.GET)
    public EntityModel<ProductResponse> productDetail(@PathVariable int productId, @RequestHeader String authorization){
        return null;
    }
}
