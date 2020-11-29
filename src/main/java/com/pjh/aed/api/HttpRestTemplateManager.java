package com.pjh.aed.api;

import com.pjh.aed.api.data.response.AEDFullDownData;
import com.pjh.aed.api.data.response.AEDResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

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

        URI uri = UriComponentsBuilder.fromUriString(apiInfo.url).queryParams(params).encode().build().toUri();
        ResponseEntity<? extends AEDResponseData> response = null;
        try {

            AEDFullDownData data = restTemplate.getForObject(uri, AEDFullDownData.class);

        ResponseEntity<Map> mapResponse = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                Map.class);

//        ResponseEntity<? extends AEDResponseData> Eresponse = restTemplate.exchange(
//                uri,
//                HttpMethod.GET,
//                httpEntity,
//                apiInfo.getAedResponseData().getClass());
        }catch (Exception e){
            log.debug(apiInfo.getApiType() + " API fail cause:" + e);
        }
        return response;
    }
}