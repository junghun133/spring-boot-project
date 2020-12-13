package com.pjh.test.daou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired


    @RequestMapping("/home")
    public String home(){

        return "home";
    }

    @PostMapping(value = "/home")
    public String homeWithProductSearch(String product){
        return "home";
    }

}
