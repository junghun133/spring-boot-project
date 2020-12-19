package com.pjh.test.daou.controller;

import com.pjh.test.daou.http.entity.ProductResponse;
import com.pjh.test.daou.service.ProductMasterService;
import com.pjh.test.daou.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AjaxRestController {
    @Autowired
    ProductMasterService productMasterService;
    @Autowired
    ProductOrderService productOrderService;

    @RequestMapping(value = "/product/more/{pageNum}",
                    method = RequestMethod.GET)
    public EntityModel<ProductResponse> loadPage(@PathVariable int pageNum){
        PageRequest pageRequest = PageRequest.of(pageNum, 9 , Sort.by(Sort.Direction.DESC, "id"));
        ProductResponse productResponse = productMasterService.findProductsPageable(pageRequest);

        EntityModel<ProductResponse> response = new EntityModel<>(productResponse);

        WebMvcLinkBuilder wmlBuilder = linkTo(methodOn(this.getClass()).loadPage(pageNum+1));
        response.add(wmlBuilder.withRel("nextRequest"));

        return response;
    }

}
