package com.kakao.pjh.configuration;

import com.kakao.pjh.config.KakaoMapAPIsConfiguration;
import com.kakao.pjh.config.KakaoLocalConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class APIConfigurationTest  {
    @Autowired
    private KakaoMapAPIsConfiguration kakaoMapAPIsConfiguration;

    @Autowired
    private KakaoLocalConfiguration kakaoLocalConfiguration;

    @Test
    public void whenFactoryProviedThenPropertiesInjectionToKakaoConfiguration(){
        assertThat(kakaoLocalConfiguration.getUrl()).isEqualTo("https://dapi.kakao.com/v2/local/search/keyword.json/");
        assertThat(kakaoLocalConfiguration.getApiKey()).isEqualTo("7822edec9750a048bde7fa98f9e796a3");
    }

    @Test
    public void whenFactoryProviedThenPropertiesInjectionToDaumMapConfiguration(){
        assertThat(kakaoMapAPIsConfiguration.getBase_url()).isEqualTo("https://map.kakao.com/link");
    }
}