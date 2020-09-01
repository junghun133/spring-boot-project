package com.pjh.aed.controller;

import com.pjh.aed.data.AEDLocation;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class AEDController {
    @RequestMapping("/v1")
    class AEDVersion1{
        @GetMapping("/find/{authId}/count")
        public List<AEDLocation> findByAddressToAEDCount(@PathVariable String authId){
            return null;
        }

        @GetMapping("/find/{authId}/list")
        public Resource<AEDLocation> findByAddressToAEDList(@PathVariable String authId){
            AEDLocation aedLocations = new AEDLocation();
            Resource<AEDLocation> resource = new Resource<>(aedLocations);


            ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(this.getClass()).findByAddressToAEDDetailList(authId,null));
            resource.add(controllerLinkBuilder.withRel("detail-location"));
            return resource;
        }


        @GetMapping("/find/{authId}/detail")
        public ResponseEntity<AEDLocation> findByAddressToAEDDetailList(@PathVariable String authId,
                                                                        @RequestBody Long locationId){

            //return ResponseEntity.created()
            return null;
        }
    }
}
