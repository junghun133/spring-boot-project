package com.kakao.pjh.controller;

import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordRequestDto;
import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordResponseDtoFromKakaoAPI;
import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordResponseToUser;
import com.kakao.pjh.service.MapSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/map")
public class MapController {
    @Autowired
    MapSearchService mapSearchService;

    @GetMapping(value = "/search/keyword", produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchByKeywordResponseToUser retrieveLocal(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
            @RequestParam(value = "query") String query,
            @RequestParam(required = false, value = "category_group_code") String category_group_code,
            @RequestParam(required = false, value = "radius") Integer radius,
            @RequestParam(required = false, value = "rect") String rect,
            @RequestParam(required = false, value = "page") Integer page,
            @RequestParam(required = false, value = "size") Integer size,
            @RequestParam(required = false, value = "sort") String sort
    ){
        SearchByKeywordRequestDto requestDto = SearchByKeywordRequestDto.builder()
                .query(query)
                .category_group_code(category_group_code)
                .radius(radius)
                .rect(rect)
                .page(page)
                .size(size)
                .sort(sort).build();
        //header apikey check logic
        return (SearchByKeywordResponseToUser) mapSearchService.process(apiKey, requestDto);
    }

    /*@GetMapping(value = "/search/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public DetailedMapDataResponseToUser detailMapData(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
            @RequestParam(value = "query") String query,
            @RequestParam(required = false, value = "category_group_code") String category_group_code,
            @RequestParam(required = false, value = "radius") Integer radius,
            @RequestParam(required = false, value = "rect") String rect,
            @RequestParam(required = false, value = "page") Integer page,
            @RequestParam(required = false, value = "size") Integer size,
            @RequestParam(required = false, value = "sort") String sort
    ){
        SearchByKeywordRequestDto requestDto = SearchByKeywordRequestDto.builder()
                .query(query)
                .category_group_code(category_group_code)
                .radius(radius)
                .rect(rect)
                .page(page)
                .size(size)
                .sort(sort).build();
        //header apikey check logic
        return (SearchByKeywordResponseToUser) mapSearchService.process(apiKey, requestDto);
    }*/
}
