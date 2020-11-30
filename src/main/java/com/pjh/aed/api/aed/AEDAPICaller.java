package com.pjh.aed.api.aed;

import com.pjh.aed.api.APICaller;
import com.pjh.aed.api.APIInfo;
import com.pjh.aed.api.HttpRestTemplateManager;
import com.pjh.aed.api.data.response.AEDResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AEDAPICaller implements APICaller {
    final HttpRestTemplateManager restTemplate;

    public AEDAPICaller(HttpRestTemplateManager restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <R extends AEDResponseData> R APICall(APIInfo apiInfo) {
        ResponseEntity responseEntity = restTemplate.sendGet(apiInfo);
        return (R) responseEntity.getBody();
    }
}
