package com.kakao.pjh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "kakao-api")
@PropertySource(value = "classpath:config/api_config.yml", factory = YamlPropertySourceFactory.class)
public class KakaoGeoConfiguration {
    String url;
    String apiKey;
}
