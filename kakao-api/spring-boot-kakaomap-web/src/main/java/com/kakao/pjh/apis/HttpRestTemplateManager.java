package com.kakao.pjh.apis;

import com.kakao.pjh.data.APIInfo;
import com.kakao.pjh.data.MultiValueMapConverter;
import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class HttpRestTemplateManager {
    public ResponseEntity sendGet(APIInfo apiInfo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, apiInfo.getConfiguration().getApiKey());
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        MultiValueMap<String, String> params = MultiValueMapConverter.convert(apiInfo.getRequest());
        URI uri = UriComponentsBuilder.fromUriString(apiInfo.getConfiguration().getUrl()).queryParams(params).build().encode().toUri();

        ResponseEntity<? extends Response> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                apiInfo.getResponse().getClass());

        return response;
    }
}