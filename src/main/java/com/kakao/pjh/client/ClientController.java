package com.kakao.pjh.client;

import org.springframework.web.bind.annotation.RequestMapping;

public class ClientController {

    @RequestMapping(value = "/loginSucess")
    public String loginSucessProcess(String apikey){
        System.out.println("apikey = " + apikey);
        return "index";
    }
}
