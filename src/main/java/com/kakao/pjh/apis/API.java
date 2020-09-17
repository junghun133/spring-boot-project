package com.kakao.pjh.apis;

import com.kakao.pjh.data.APIInfo;
import com.kakao.pjh.data.dto.Response;

public interface API {
    enum APIs{
        KAKAO_LOCAL, KAKAO_BIZMESSAGE //....TODO
    }

    enum APIDetailType{
        KAKAO_MAP,
        KAKAO_ROAD,
        KAKAO_ROADVIEW,
        KAKAO_SEARCH
    }

    <R extends Response> R APICall(APIInfo apiInfo);
}
