package com.kakao.pjh.apis;

import com.kakao.pjh.data.APIInfo;
import com.kakao.pjh.data.dto.Response;
import org.springframework.stereotype.Component;

@Component
public class KakaoBizMessageAPI implements API{

    @Override
    public <R extends Response> R APICall(APIInfo apiInfo) {
        return null;
    }
}
