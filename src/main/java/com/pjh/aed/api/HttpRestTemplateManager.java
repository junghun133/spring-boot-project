package com.pjh.aed.api;

import com.pjh.aed.api.data.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class HttpRestTemplateManager {
    RestTemplate restTemplate;

    public ResponseEntity sendGet(APIInfo apiInfo) {
        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.AUTHORIZATION, apiInfo.getConfiguration().getApiKey());
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        MultiValueMap<String, String> params = MultiValueMapConverter.convert(apiInfo.getRequest());
        URI uri = UriComponentsBuilder.fromUriString(apiInfo.url).queryParams(params).build().encode().toUri();

        ResponseEntity<? extends ResponseData> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                apiInfo.getResponseData().getClass());

        return response;
    }
}