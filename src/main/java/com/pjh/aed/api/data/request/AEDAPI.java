package com.pjh.aed.api.data.request;

import com.pjh.aed.api.APICaller;
import com.pjh.aed.api.APIInfo;
import com.pjh.aed.api.HttpRestTemplateManager;
import com.pjh.aed.api.data.response.AEDResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AEDAPI implements APICaller {
    @Autowired
    HttpRestTemplateManager httpRestTemplateManager;

    @Override
    public <R extends AEDResponseData> R APICall(APIInfo apiInfo) {
        ResponseEntity responseEntity = httpRestTemplateManager.sendGet(apiInfo);
        return (R) responseEntity.getBody();
    }
}
