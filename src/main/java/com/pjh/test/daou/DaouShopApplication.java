package com.pjh.test.daou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class DaouShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaouShopApplication.class, args);
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
