package com.pjh.test.daou;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class DaouShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaouShopApplication.class, args);
    }


}
