package com.pjh.aed.api;

import com.pjh.aed.api.data.request.AEDRequestData;
import com.pjh.aed.api.data.response.AEDResponseData;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class APIInfo {
    APICaller.APIs apiType;
    String url;

    AEDRequestData request;
    AEDResponseData AEDResponseData;
}
