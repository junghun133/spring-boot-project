package com.pjh.test.daou.controller.api;

import com.pjh.test.daou.config.auth.JWTService;
import com.pjh.test.daou.config.auth.JWTUtil;
import com.pjh.test.daou.domain.Member;
import com.pjh.test.daou.exception.NotFoundMemberException;
import com.pjh.test.daou.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("/api/v1/auth")
public class AuthController {
    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(value = "/create",
            method = RequestMethod.GET)
    public EntityModel<AuthorizationResponse> createAuth(@RequestParam String id,
                                                   @RequestParam String password){

        Optional<Member> member = memberRepository.findByAccountAndPassword(id, password);
        member.orElseThrow(NotFoundMemberException::new);

        String token = jwtUtil.generateToken(id);



//        EntityModel<ProductResponse> response = new EntityModel<>(productResponse);
//        WebMvcLinkBuilder wmlBuilder = linkTo(methodOn(this.getClass()).loadPage(pageNum+1));
//        response.add(wmlBuilder.withRel("nextRequest"));
        return null;
    }

    private class AuthorizationResponse {
        String apikey;
    }
}
