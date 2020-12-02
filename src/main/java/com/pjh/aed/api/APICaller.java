package com.pjh.aed.api;

import com.pjh.aed.api.data.response.GO_AEDResponseData;

public interface APICaller {
    enum APIs {
        PUBLIC_DATA_AED, ETC_API;
    }

    <R extends GO_AEDResponseData> R APICall(APIInfo apiInfo);
}
