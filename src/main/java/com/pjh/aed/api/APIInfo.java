package com.pjh.aed.api;

import com.pjh.aed.api.data.request.AEDRequestData;
import com.pjh.aed.api.data.response.AEDResponseData;
import lombok.*;

import java.net.URI;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class APIInfo {
    APICaller.APIs apiType;
    URI uri;

    AEDRequestData aedRequestData;
    AEDResponseData aedResponseData;
}
