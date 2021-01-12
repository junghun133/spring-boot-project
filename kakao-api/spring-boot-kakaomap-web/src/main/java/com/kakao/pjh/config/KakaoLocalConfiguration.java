package com.kakao.pjh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "local-api")
@PropertySource(value = "classpath:config/api_config.yml", factory = YamlPropertySourceFactory.class)
public class KakaoLocalConfiguration implements Configuration{
    String url;
    String apiKey;
}
