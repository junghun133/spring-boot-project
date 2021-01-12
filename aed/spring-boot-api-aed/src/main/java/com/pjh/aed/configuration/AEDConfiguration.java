package com.pjh.aed.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Data
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "api-info")
@PropertySource(value = "classpath:config/api_config.yml", factory = YamlPropertySourceFactory.class)
public class AEDConfiguration {
    String url;
    String apikey;
    String dataformat;

    public List<String> data = new ArrayList<>();
}
