package com.pjh.aed.http;

import com.pjh.aed.api.APIInfo;
import com.pjh.aed.api.data.response.AEDResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HttpRestTemplateManager {
    RestTemplate restTemplate;

    public ResponseEntity sendGet(APIInfo apiInfo) {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
//        MultiValueMap<String, String> params = null;
        ResponseEntity<? extends AEDResponseData> response = null;
        try {
//        if(apiInfo.getAedRequestData() != null)
//            params = MultiValueMapConverter.convert(apiInfo.getAedRequestData());
            response = restTemplate.exchange(
                apiInfo.getUri(),
                HttpMethod.GET,
                httpEntity,
                apiInfo.getAedResponseData().getClass());

        } catch (Exception e){
            log.debug(apiInfo.getApiType() + " API fail cause:" + e);
        }

        return response;
    }
}