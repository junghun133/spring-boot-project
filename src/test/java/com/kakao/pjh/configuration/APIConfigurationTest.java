package com.kakao.pjh.configuration;

import com.kakao.pjh.config.DaumMapAPIConfiguration;
import com.kakao.pjh.config.KakaoGeoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class APIConfigurationTest  {
    @Autowired
    private DaumMapAPIConfiguration daumMapAPIConfiguration;

    @Autowired
    private KakaoGeoConfiguration kakaoGeoConfiguration;

    @Test
    public void whenFactoryProviedThenPropertiesInjectionToKakaoConfiguration(){
        assertThat(kakaoGeoConfiguration.getUrl()).isEqualTo("https://dapi.kakao.com/v2/local/search/keyword.json/");
        assertThat(kakaoGeoConfiguration.getApiKey()).isEqualTo("7822edec9750a048bde7fa98f9e796a3");
    }

    @Test
    public void whenFactoryProviedThenPropertiesInjectionToDaumMapConfiguration(){
        assertThat(daumMapAPIConfiguration.getUrl()).isEqualTo("https://map.kakao.com/link/map/");
        assertThat(daumMapAPIConfiguration.getApiKey()).isEqualTo("bdcd97024c926cda52b32dc9b30a51fa");
    }
}