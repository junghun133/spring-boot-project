package com.pjh.aed.controller;

import com.pjh.aed.data.request.AEDLocationRequestData;
import com.pjh.aed.service.AEDFullDownServiceRunner;
import com.pjh.aed.service.executor.ServiceRequest;
import com.pjh.aed.service.executor.ServiceRunnerExecutor;
import com.pjh.aed.service.executor.ServiceRunnerType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/aed/v1")
@RequiredArgsConstructor
public class AEDController {
    AEDFullDownServiceRunner aedFullDownServiceRunner;
    private ServiceRunnerExecutor serviceExecutor;

    //AED 전체 목록
    @GetMapping("/find/{token}/all")
    public String findAEDInfoAll(@PathVariable String token){
        ServiceRequest request = ServiceRequest.createServiceRequest(
                AEDLocationRequestData.AEDLocationRequestDataBuilder().token(token).build(),
                ServiceRunnerType.find(ServiceRunnerType.AED_FULLDOWN)
                );

        return serviceExecutor.execute(request);
    }

    //AED 지역 조회
    @GetMapping("/find/{token}/list")
    public String findAEDInfoPage(@PathVariable String token){
        AEDLocationRequestData aedLocationRequestData = new AEDLocationRequestData();
        return null;
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