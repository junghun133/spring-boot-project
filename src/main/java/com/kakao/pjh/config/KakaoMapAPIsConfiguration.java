package com.kakao.pjh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "map-url-info")
@PropertySource(value = "classpath:config/api_config.yml", factory = YamlPropertySourceFactory.class)
public class KakaoMapAPIsConfiguration implements Configuration{
    String base_url;
    public List<Map<String, String>> items = new ArrayList<>();

    @Override
    public String getUrl() {
        return base_url;
    }

    @Override
    public String getApiKey() {
        return null;
    }
}
