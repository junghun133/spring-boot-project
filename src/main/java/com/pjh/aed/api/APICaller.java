package com.pjh.aed.api;

import com.pjh.aed.api.data.response.ResponseData;

public interface APICaller {
    enum APIs {
        PUBLIC_DATA_AED, ETC_API;
    }

    <R extends ResponseData> R APICall(APIInfo apiInfo);
}
