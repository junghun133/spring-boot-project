package com.pjh.springkotlinapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Helloworld {
    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }
}
