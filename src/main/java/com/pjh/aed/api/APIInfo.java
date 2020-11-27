package com.pjh.aed.api;

import com.pjh.aed.api.data.request.RequestData;
import com.pjh.aed.api.data.response.ResponseData;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class APIInfo {
    APICaller.APIs apiType;
    String url;

    RequestData request;
    ResponseData responseData;
}
