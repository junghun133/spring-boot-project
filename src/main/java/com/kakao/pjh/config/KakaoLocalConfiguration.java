package com.kakao.pjh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "local-api")
@PropertySource(value = "classpath:config/api_config.yml", factory = YamlPropertySourceFactory.class)
public class KakaoLocalConfiguration {
    String url;
    String apiKey;
}
