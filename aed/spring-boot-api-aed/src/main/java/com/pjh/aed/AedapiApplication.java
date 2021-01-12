package com.pjh.aed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class AedapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AedapiApplication.class, args);
    }

}
