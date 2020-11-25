package com.pjh.aed.api.data.request;

import com.pjh.aed.api.API;
import com.pjh.aed.api.APIInfo;
import com.pjh.aed.api.HttpRestTemplateManager;
import com.pjh.aed.api.data.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AEDAPI implements API {
    @Autowired
    HttpRestTemplateManager httpRestTemplateManager;

    @Override
    public <R extends ResponseData> R APICall(APIInfo apiInfo) {
        ResponseEntity responseEntity = httpRestTemplateManager.sendGet(apiInfo);
        return (R) responseEntity.getBody();
    }
}
