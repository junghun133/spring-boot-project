package com.kakao.pjh.apis;

import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;

public interface Http<T extends Request >{
    void setRequestType(Request request);
    void setUrl(String url);
    Class<? extends Response> send(String urlParam);
}
