package com.pjh.aed.api;

import com.pjh.aed.api.data.request.RequestData;
import com.pjh.aed.api.data.response.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class APIInfo {
    API.APIs apiType;
    String url;

    RequestData request;
    ResponseData responseData;
}
