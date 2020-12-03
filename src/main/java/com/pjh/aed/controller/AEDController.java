package com.pjh.aed.controller;

import com.pjh.aed.data.request.AEDLocationRequestData;
import com.pjh.aed.service.executor.ServiceRequest;
import com.pjh.aed.service.executor.ServiceRunnerExecutor;
import com.pjh.aed.service.executor.ServiceRunnerType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;


@RestController
@RequestMapping("/aed/v1")
public class AEDController {
    private final ServiceRunnerExecutor serviceExecutor;

    public AEDController(ServiceRunnerExecutor serviceExecutor) {
        this.serviceExecutor = serviceExecutor;
    }

    //AED 전체 목록
    @GetMapping(value = "/find/{token}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findAEDInfoAll(@PathVariable String token, Integer pageNo, Integer numOfRows){
        ServiceRequest request = ServiceRequest.createServiceRequest(
                AEDLocationRequestData.AEDLocationRequestDataBuilder()
                        .token(token)
                        .pageNo(pageNo)
                        .numOfRows(numOfRows)
                        .build(),
                ServiceRunnerType.find(ServiceRunnerType.AED_FULLDOWN)
                );

        return serviceExecutor.execute(request);
    }

    //AED 지역 조회
    @GetMapping("/find/{token}/address")
    public String findAEDInfoFromAddress(@PathVariable String token,
                                  @RequestParam(required = true, value = "state") String state,
                                  @RequestParam(required = true, value = "county") String county
                                  ){
        ServiceRequest request = ServiceRequest.createServiceRequest(
                AEDLocationRequestData.AEDLocationRequestDataBuilder()
                        .token(token)
                        .state(state)
                        .county(county)
                        .build(),
                ServiceRunnerType.find(ServiceRunnerType.AED_LOCATION_INQUIRE)
        );

        return serviceExecutor.execute(request);
    }

    //AED 지역 조회 데이터와 응급의료 전달
    /*@GetMapping("/find/{authId}/list/ermct")
    public Resource<AEDLocation> findAEDInfoPageWithErmctInfo(@PathVariable String authId){
        AEDLocation aedLocations = new AEDLocation();
        Resource<AEDLocation> resource = new Resource<>(aedLocations);

        //ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(this.getClass()).findByAddressToAEDDetailList(authId,null));
        //resource.add(controllerLinkBuilder.withRel("detail-location"));
        return resource;
    }*/
}