package com.kakao.pjh.apis;

import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordResponseDtoFromKakaoAPI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HttpRestTemplateManagerTest {

    private final String BASE_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    @Test
    public void KakaoHttpSendTest(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 7822edec9750a048bde7fa98f9e796a3");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL).queryParam("query","카카오 뱅크");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<SearchByKeywordResponseDtoFromKakaoAPI> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, SearchByKeywordResponseDtoFromKakaoAPI.class);

        assertThat(exchange.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(exchange.hasBody()).isEqualTo(true);
        assertThat(exchange.getHeaders().isEmpty()).isEqualTo(false);
    }
}