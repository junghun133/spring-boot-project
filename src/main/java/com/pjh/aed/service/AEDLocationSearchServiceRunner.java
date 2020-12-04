package com.pjh.aed.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjh.aed.api.APICaller;
import com.pjh.aed.api.APIFactory;
import com.pjh.aed.api.APIInfo;
import com.pjh.aed.api.data.request.GO_AEDFullDownRequestData;
import com.pjh.aed.api.data.request.GO_AEDLocationRequestData;
import com.pjh.aed.api.data.response.*;
import com.pjh.aed.configuration.AEDConfiguration;
import com.pjh.aed.configuration.APIDetailCode;
import com.pjh.aed.dao.UserDao;
import com.pjh.aed.data.Result;
import com.pjh.aed.data.response.AEDInfoResponse;
import com.pjh.aed.exception.AEDInternalServerException;
import com.pjh.aed.http.URIAssemble;
import com.pjh.aed.service.executor.ServiceRequest;
import com.pjh.aed.service.executor.ServiceRunnerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AEDLocationSearchServiceRunner implements ServiceRunnerInterface {
    APIFactory apiFactory;
    AEDConfiguration aedConfiguration;
    ObjectMapper objectMapper;
    final APICaller.APIs apiType = APICaller.APIs.PUBLIC_DATA_AED;

    @Autowired
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
        URIAssemble uriAssemble = new URIAssemble();
        uriAssemble.basicAEDURIAddress(url, aedConfiguration.getApikey()).addParam(request);

        APIInfo apiInfo = APIInfo.builder()
                .apiType(apiType)
                .GOAedRequestData(new GO_AEDLocationRequestData())
                .GOAedResponseData(new GO_AEDLocationResponseData())
                .uri(uriAssemble.getUri())
                .build();

        GO_AEDResponseData GOAedResponseData = apiCaller.APICall(apiInfo);

        GO_AEDLocationResponseData response = null;

        if(GOAedResponseData instanceof GO_AEDFullDownResponse)
            response = (GO_AEDLocationResponseData) GOAedResponseData;

        //set response
        GO_AEDItems body = response.getBody();
        List<AEDInfoResponse.AED> data = new ArrayList<>();
        for (GO_AEDItemDto item : body.getItems()) {
            data.add(AEDInfoResponse.AED.builder()
                    .buildPlace(item.getBuildPlace())
                    .buildAddress(item.getBuildAddress())
                    .clerkTel(item.getClerkTel())
                    .model(item.getModel())
                    .wgs84Lat(item.getWgs84Lat())
                    .wgs84Lon(item.getWgs84Lon())
                    .distance(item.getDistance())
                    .build()
            );
        }

        String result = null;
        try {
            result = objectMapper.writeValueAsString(AEDInfoResponse.builder()
                    .code(Result.Code.SUCC.getValue())
                    .message(Result.DetailMessage.Success.getCause())
                    .page(new AEDInfoResponse.Page(body.getNumOfRows(), body.getPageNo(), body.getTotalCount()))
                    .data(data)
                    .build());
        } catch (JsonProcessingException e) {
            throw new AEDInternalServerException();
        }
        return result;
    }
}
