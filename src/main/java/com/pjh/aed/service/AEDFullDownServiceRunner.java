package com.pjh.aed.service;

import com.pjh.aed.api.APICaller;
import com.pjh.aed.api.APIFactory;
import com.pjh.aed.api.APIInfo;
import com.pjh.aed.api.data.request.AEDFullDownRequestData;
import com.pjh.aed.api.data.response.AEDFullDownResponse;
import com.pjh.aed.api.data.response.AEDResponseData;
import com.pjh.aed.configuration.AEDConfiguration;
import com.pjh.aed.configuration.APIDetailCode;
import com.pjh.aed.http.URIAssemble;
import com.pjh.aed.service.executor.ServiceRequest;
import com.pjh.aed.service.executor.ServiceRunnerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class AEDFullDownServiceRunner implements ServiceRunnerInterface {
    APIFactory apiFactory;
    AEDConfiguration aedConfiguration;
    final APICaller.APIs apiType = APICaller.APIs.PUBLIC_DATA_AED;

    @Autowired
    public AEDFullDownServiceRunner(APIFactory apiFactory, AEDConfiguration aedConfiguration) {
        this.apiFactory = apiFactory;
        this.aedConfiguration = aedConfiguration;
        aedConfiguration.getData().get(2);
    }


    @Override
    public String runService(ServiceRequest request) {
        APICaller apiCaller = apiFactory.getAPICaller(apiType);
        List<String> detailAddress = aedConfiguration.getData();
        String url = aedConfiguration.getUrl() + detailAddress.get(APIDetailCode.AEDAPIDetailCode.FULL_DATA.getCode());
        AEDFullDownRequestData requestData = new AEDFullDownRequestData();

        URIAssemble uriAssemble = new URIAssemble(url, aedConfiguration.getApikey());

        APIInfo apiInfo = APIInfo.builder()
                .apiType(apiType)
                .aedRequestData(requestData)
                .aedResponseData(new AEDFullDownResponse())
                .uri(uriAssemble.basicAEDURIAddress())
                .build();

        AEDResponseData AEDResponseData = apiCaller.APICall(apiInfo);
        //AEDResponse -> Response
        return null;
    }
}
