package com.pjh.web.shop.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIFilterConfiguration {

    @Bean
    public FilterRegistrationBean<APIFilter> filter(){
        FilterRegistrationBean<APIFilter> bean = new FilterRegistrationBean<>(new APIFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 낮은 번호순으로 필터실행
        return bean;
    }

}
