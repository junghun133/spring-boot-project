package com.kakao.pjh.controller;

import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordRequestDto;
import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordResponseDto;
import com.kakao.pjh.service.MapSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/map")
public class MapSearchController {
    @Autowired
    MapSearchService mapSearchService;

    @GetMapping(value = "/search/keyword")
    public SearchByKeywordResponseDto retrieveLocal(
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
        return (SearchByKeywordResponseDto) mapSearchService.process(requestDto);
    }
}
