package com.pjh.aed.controller;

import com.pjh.aed.data.dto.AEDLocation;
import com.pjh.aed.service.AEDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/aed/v1")
public class AEDController {
    @Autowired
    AEDService aedService;

    //AED 전체 목록
    @GetMapping("/find/{authId}/all")
    public List<AEDLocation> findAEDInfoAll(@PathVariable String authId){
        return null;
    }

    //AED 지역 조회
    @GetMapping("/find/{authId}/list")
    public List<AEDLocation> findAEDInfoPage(@PathVariable String authId){
        AEDLocation aedLocation = new AEDLocation();
        List<AEDLocation> aedLocations = new ArrayList<>();
        return aedLocations;
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