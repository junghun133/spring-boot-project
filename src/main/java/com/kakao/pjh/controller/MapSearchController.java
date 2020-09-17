package com.kakao.pjh.controller;

import com.kakao.pjh.apis.API;
import com.kakao.pjh.data.dto.SearchByKeywordRequestDto;
import com.kakao.pjh.data.dto.SearchByKeywordResponseDto;
import com.kakao.pjh.service.MapSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/map")
public class MapSearchController {
    @Autowired
    MapSearchService mapSearchService;

    @GetMapping(value = "/search")
    public SearchByKeywordResponseDto retrieveLocal(@RequestBody @Valid SearchByKeywordRequestDto searchByKeywordRequestDto){
        return (SearchByKeywordResponseDto) mapSearchService.search(searchByKeywordRequestDto, API.APIs.KAKAO_LOCAL);
    }
}
