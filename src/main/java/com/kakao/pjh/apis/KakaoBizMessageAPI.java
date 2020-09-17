package com.kakao.pjh.apis;

import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
import org.springframework.stereotype.Component;

@Component
public class KakaoBizMessageAPI implements API{
    @Override
    public <R extends Response> R APICall(Request request, APIDetailType apiDetailType) {
        return null;
    }
}
