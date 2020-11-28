package com.pjh.aed.api;

import com.pjh.aed.api.data.response.AEDResponseData;

public interface APICaller {
    enum APIs {
        PUBLIC_DATA_AED, ETC_API;
    }

    <R extends AEDResponseData> R APICall(APIInfo apiInfo);
}
