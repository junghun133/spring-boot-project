package com.pjh.test.daou.controller.api;


import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/member")
public class MemberController {

    @RequestMapping(value = "/mypage",
            method = RequestMethod.GET)
    public EntityModel<MemberAPIResponse> createAuth(@RequestParam String id,
                                                                        @RequestHeader String authorization){

//        EntityModel<ProductResponse> response = new EntityModel<>(productResponse);
//        WebMvcLinkBuilder wmlBuilder = linkTo(methodOn(this.getClass()).loadPage(pageNum+1));
//        response.add(wmlBuilder.withRel("nextRequest"));
        return null;
    }

    private class MemberAPIResponse {

    }
}
