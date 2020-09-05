package com.pjh.aed.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "ermct-info")
@PropertySource(value = "classpath:config/api_config.yml", factory = YamlPropertySourceFactory.class)
public class ErmctConfiguration {
    String url;
    String apikey;
    String dataformat;

    public List<Map<String, String>> data = new ArrayList<Map<String, String>>();
}
