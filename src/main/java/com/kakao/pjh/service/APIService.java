package com.kakao.pjh.service;

import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;

public interface APIService {
    Response process(String apiKey, Request request);
}
