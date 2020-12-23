package com.pjh.test.daou.controller;

import com.pjh.test.daou.dto.MemberTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("memberTo", new MemberTO());

        return "login";
    }

    @PostMapping("/login")
    public String login(MemberTO memberTo){
        return "redirect:/home";
    }

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("memberTo", new MemberTO());

        return "join";
    }

    @PostMapping("/join")
    public String join(MemberTO memberTo){
        return "redirect:/home";
    }

}
