package com.kakao.pjh.apis;

import com.kakao.pjh.data.APIInfo;
import com.kakao.pjh.data.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class KakaoLocalAPI implements API {
    @Autowired
    HttpRestTemplateManager httpRestTemplateManager;

    @Override
    public <R extends Response> R APICall(APIInfo apiInfo) {
        ResponseEntity<Response> responseEntity = httpRestTemplateManager.sendGet(apiInfo);
        return (R) responseEntity.getBody();
    }
}
