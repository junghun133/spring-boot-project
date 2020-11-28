package com.pjh.aed.service;

import com.pjh.aed.api.APICaller;
import com.pjh.aed.api.APIFactory;
import com.pjh.aed.api.APIInfo;
import com.pjh.aed.api.data.response.AEDResponseData;
import com.pjh.aed.configuration.AEDConfiguration;
import com.pjh.aed.service.executor.ServiceRequest;
import com.pjh.aed.service.executor.ServiceRunnerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AEDFullDownServiceRunner implements ServiceRunnerInterface {
    APIFactory apiFactory;
    AEDConfiguration aedConfiguration;

    @Autowired
    public AEDFullDownServiceRunner(APIFactory apiFactory, AEDConfiguration aedConfiguration) {
        this.apiFactory = apiFactory;
        this.aedConfiguration = aedConfiguration;
    }

    APICaller.APIs apiType = APICaller.APIs.PUBLIC_DATA_AED;

    @Override
    public String runService(ServiceRequest request) {
        APICaller apiCaller = apiFactory.getAPICaller(apiType);
//      request -> AEDReqeust

        APIInfo apiInfo = APIInfo.builder()
                .apiType(apiType)
                .request(null)
                .url(aedConfiguration.getUrl() + aedConfiguration.getApikey())
                .build();

        AEDResponseData AEDResponseData = apiCaller.APICall(apiInfo);
        //AEDResponse -> Response

        return null;
    }
}
