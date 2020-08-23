package com.pjh.aed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AEDController {
    @RequestMapping("/v1")
    class AEDVersion1{
        @GetMapping("/find/{address}/count")
        public Integer findByAddressToAEDCount(@PathVariable String address){
            return 0;
        }
    }
}
