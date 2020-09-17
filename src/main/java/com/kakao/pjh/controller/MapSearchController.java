package com.kakao.pjh.controller;

import com.kakao.pjh.service.MapSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/map")
public class MapSearchController {
    @Autowired
    MapSearchService mapSearchService;

}
