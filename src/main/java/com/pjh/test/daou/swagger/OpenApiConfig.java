package com.pjh.test.daou.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Info info = new Info().title("옷뿌리오 API").version(appVersion)
                .description("옷뿌리오 API Swagger 화면입니다.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact().name("Park").url("https://github.com/junghun133").email("junghun5947@gmail.com"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}

