package com.pjh.aed.api;

import com.pjh.aed.api.data.request.RequestData;
import com.pjh.aed.api.data.response.ResponseData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class APIInfo {
    API.APIs apiType;

    RequestData request;
    ResponseData responseData;

}
