package com.pjh.test.daou.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String landing(){
        return "landing";
    }

    @RequestMapping("/manager")
    public String manager(){
        return "manager";
    }


    @RequestMapping("/home")
    public String home(){
        return "home";
    }
}
