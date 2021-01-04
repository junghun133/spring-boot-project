package com.pjh.web.shop.controller;

import com.pjh.web.shop.config.auth.PrincipalDetails;
import com.pjh.web.shop.dto.MemberTO;
import com.pjh.web.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("memberTo", new MemberTO());

        return "login";
    }
/*
    @PostMapping("/login")
    public String login(MemberTO memberTo){
        memberService.loadUserByUsername(memberTo.getAccount());
        return "redirect:/home";
    }*/

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("memberTo", new MemberTO());

        return "join";
    }

    @PostMapping("/join")
    public String join(MemberTO memberTo){

        memberService.save(memberTo.toEntity());

        return "redirect:/login";
    }

    @PostMapping("/idCheck")
    @ResponseBody
    public Integer idCheck(HttpServletRequest request){
//        boolean idCheck = memberService.idCheck(memberTo.getAccount());
        boolean idCheck = memberService.idCheck(request.getParameter("account"));

        return idCheck ? 1 : 0;
    }

    @GetMapping("/idCheck")
    @ResponseBody
    public Integer idCheck(){

        return 1;
    }

    @ResponseBody
    @GetMapping("/userInfo")
    public String userInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        System.out.println(principalDetails.getAttributes());
        return "session check";
    }
}
