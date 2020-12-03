package com.pjh.aed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjh.aed.api.APICaller;
import com.pjh.aed.api.APIFactory;
import com.pjh.aed.api.APIInfo;
import com.pjh.aed.api.data.request.GO_AEDFullDownRequestData;
import com.pjh.aed.api.data.request.GO_AEDLocationRequestData;
import com.pjh.aed.api.data.response.GO_AEDFullDownResponse;
import com.pjh.aed.api.data.response.GO_AEDLocationResponseData;
import com.pjh.aed.api.data.response.GO_AEDResponseData;
import com.pjh.aed.configuration.AEDConfiguration;
import com.pjh.aed.configuration.APIDetailCode;
import com.pjh.aed.dao.UserDao;
import com.pjh.aed.http.URIAssemble;
import com.pjh.aed.service.executor.ServiceRequest;
import com.pjh.aed.service.executor.ServiceRunnerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AEDLocationSearchServiceRunner implements ServiceRunnerInterface {
    APIFactory apiFactory;
    AEDConfiguration aedConfiguration;
    ObjectMapper objectMapper;
    final APICaller.APIs apiType = APICaller.APIs.PUBLIC_DATA_AED;

    public AEDLocationSearchServiceRunner(APIFactory apiFactory, AEDConfiguration aedConfiguration, ObjectMapper objectMapper) {
        this.apiFactory = apiFactory;
        this.aedConfiguration = aedConfiguration;
        this.objectMapper = objectMapper;
    }

    @Override
    public String runService(ServiceRequest request) {
        APICaller apiCaller = apiFactory.getAPICaller(apiType);
        List<String> detailAddress = aedConfiguration.getData();

        //make url
        String url = aedConfiguration.getUrl() + detailAddress.get(APIDetailCode.AEDAPIDetailCode.LOCATION_INFO.getCode());
        URIAssemble uriAssemble = new URIAssemble(url, aedConfiguration.getApikey());

        APIInfo apiInfo = APIInfo.builder()
                .apiType(apiType)
                .GOAedRequestData(new GO_AEDLocationRequestData())
                .GOAedResponseData(new GO_AEDLocationResponseData())
                .uri(uriAssemble.basicAEDURIAddress())
                .build();

        GO_AEDResponseData GOAedResponseData = apiCaller.APICall(apiInfo);
        return null;
    }
}
