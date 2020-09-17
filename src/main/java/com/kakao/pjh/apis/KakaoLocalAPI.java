package com.kakao.pjh.apis;

import com.kakao.pjh.config.KakaoLocalConfiguration;
import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KakaoLocalAPI implements API {
    @Autowired
    KakaoLocalConfiguration kakaoLocalConfiguration;
    @Autowired
    HttpRestTemplateManager httpRestTemplateManager;

    @Override
    public Response APICall(Request request, APIDetailType apiDetailType) {
        Response response = httpRestTemplateManager.send(null, request);
        return response;
    }
}
