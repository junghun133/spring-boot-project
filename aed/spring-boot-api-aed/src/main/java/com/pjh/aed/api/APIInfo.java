package com.pjh.aed.api;

import com.pjh.aed.api.data.request.GO_AEDRequestData;
import com.pjh.aed.api.data.response.GO_AEDResponseData;
import lombok.*;

import java.net.URI;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class APIInfo {
    APICaller.APIs apiType;
    URI uri;

    GO_AEDRequestData GOAedRequestData;
    GO_AEDResponseData GOAedResponseData;
}
