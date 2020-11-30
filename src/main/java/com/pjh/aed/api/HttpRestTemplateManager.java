package com.pjh.aed.api;

import com.pjh.aed.api.data.response.AEDResponseData;
import com.pjh.aed.exception.APIURISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class HttpRestTemplateManager {
    RestTemplate restTemplate;

    public ResponseEntity sendGet(APIInfo apiInfo) {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.AUTHORIZATION, apiInfo.getConfiguration().getApiKey());
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        MultiValueMap<String, String> params = null;

        if(apiInfo.getAedRequestData() != null)
            params = MultiValueMapConverter.convert(apiInfo.getAedRequestData());

        URI uri = null;
        ResponseEntity<? extends AEDResponseData> response = null;
        try {
            uri = new URI(apiInfo.url + "?serviceKey=" + params.getFirst("serviceKey"));
//        AEDFullDownData data = restTemplate.getForObject(uri, AEDFullDownData.class);
            response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                apiInfo.getAedResponseData().getClass());

        } catch (URISyntaxException e) {
            log.debug("URI SyntaxException.. url:" + apiInfo.url);
            throw new APIURISyntaxException();
        } catch (Exception e){
            log.debug(apiInfo.getApiType() + " API fail cause:" + e);
        }
        return response;
    }
}