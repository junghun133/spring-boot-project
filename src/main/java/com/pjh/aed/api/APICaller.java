package com.pjh.aed.api;

import com.pjh.aed.api.data.response.GO_AEDResponseData;

public interface APICaller {
    enum APIs {
        PUBLIC_DATA_AED, //정부공공데이터
        ETC_API; //추후 연동될 API
    }

    <R extends GO_AEDResponseData> R APICall(APIInfo apiInfo);
}
