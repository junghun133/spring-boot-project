package com.kakao.pjh.apis;

import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class HttpRestTemplateManager {
    String destinationUrl = "";
    private Request request;

    public Response send(String urlParam, Request request) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(destinationUrl).queryParam(urlParam);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON_VALUE));

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        return (Response) restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, request.getClass()).getBody();
    }
}