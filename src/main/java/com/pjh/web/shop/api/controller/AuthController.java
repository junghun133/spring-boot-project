package com.pjh.web.shop.api.controller;

import com.pjh.web.shop.api.service.MemberAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/api/v1/auth")
public class AuthController {
    @Autowired
    MemberAPIService memberAPIService;

    @PostMapping(value = "/create")
    public EntityModel<AuthorizationResponse> apiLogin(@RequestParam String account,
                                                   @RequestParam String password){

        System.out.println("account = " + account);
        System.out.println("password = " + password);
        memberAPIService.apilogin(account, password);

//        EntityModel<ProductResponse> response = new EntityModel<>(productResponse);
//        WebMvcLinkBuilder wmlBuilder = linkTo(methodOn(this.getClass()).loadPage(pageNum+1));
//        response.add(wmlBuilder.withRel("nextRequest");
        return null;
    }

    private class AuthorizationResponse {
        String apikey;
    }
}
