package com.pjh.aed.controller;

import com.pjh.aed.data.request.AEDLocationRequestData;
import com.pjh.aed.data.request.LocationRequestData;
import com.pjh.aed.service.ErmctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/ermct/v1")
public class ErmctInfoInquireController {
    @Autowired
    ErmctService ermctService;

    //응급의료기관 위치 정보 조회
    @PostMapping("/find/{authId}/location")
    public ResponseEntity<AEDLocationRequestData> findByAddressToAEDDetailList(@PathVariable String authId, @RequestBody LocationRequestData locationRequestData){
        //return ResponseEntity.created()
        return null;
    }
}
