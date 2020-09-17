package com.kakao.pjh.apis;

import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HttpRestTemplateManagerTest {

    private final String BASE_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    @Test
    public void KakaoHttpSendTest(){
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "school");

        ResponseEntity<SearchByKeywordResponseDto> responseEntity = restTemplate.getForEntity(BASE_URL, SearchByKeywordResponseDto.class, params);
        System.out.println("responseEntity = " + responseEntity);
    }
}