package com.pjh.aed.api.aed;

import com.pjh.aed.api.APICaller;
import com.pjh.aed.api.APIInfo;
import com.pjh.aed.api.HttpRestTemplateManager;
import com.pjh.aed.api.data.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AEDAPICaller implements APICaller {
    HttpRestTemplateManager restTemplate;

    @Override
    public <R extends ResponseData> R APICall(APIInfo apiInfo) {
        return (R) restTemplate.sendGet(apiInfo);
    }
}
