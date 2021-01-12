package com.kakao.pjh.data;

import com.kakao.pjh.apis.API;
import com.kakao.pjh.config.Configuration;
import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIInfo {
    API.APIs apis;
    API.APIDetailType apiDetailType;

    Request request;
    Response response;
    Configuration configuration;
}
