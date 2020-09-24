package com.kakao.pjh.controller;

import com.kakao.pjh.apis.API;
import com.kakao.pjh.data.dto.detail.DetailMapRequestDto;
import com.kakao.pjh.data.dto.detail.DetailSearchResponseToUser;
import com.kakao.pjh.data.dto.popular.PopularKeywordResponseToUser;
import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordRequestDto;
import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordResponseToUser;
import com.kakao.pjh.service.MapDetailSearchService;
import com.kakao.pjh.service.MapPopularKeywordService;
import com.kakao.pjh.service.MapSearchService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/map")
public class MapController {
    @Autowired
    MapSearchService mapSearchService;
    @Autowired
    MapDetailSearchService mapDetailSearchService;
    @Autowired
    MapPopularKeywordService mapPopularKeywordService;



    //전체 map 정보 조회
    @ApiOperation(value = "search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "검색어", required = true, dataType = "String", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "category_group_code", value = "카테고리 그룹 이름", required = false, dataType = "String", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "radius", value = "중심 좌표로부터 반경거리", required = false, dataType = "Integer", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "rect", value = "사각형 범위내에서 제한 검색을 위한 좌표", required = false, dataType = "String", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "page", value = "결과 페이지 번호, 1 ~ 45", required = false, dataType = "Integer", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "한 페이지에 보여질 문서의 개수, 1 ~ 15", required = false, dataType = "Integer", paramType = "query", defaultValue = "15"),
            @ApiImplicitParam(name = "sort", value = "결과 정렬 순서 distance, accuracy", required = false, dataType = "String", paramType = "query", defaultValue = "distance")
    })
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/search/keyword", produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchByKeywordResponseToUser retrieveMapInfo(
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

        return (SearchByKeywordResponseToUser) mapSearchService.process(apiKey, requestDto);
    }

    // keyword id를 통한 detail 조회
    @ApiOperation(value = "search_detail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "키워드 검색을 통해 응답받은 Map 고유 id", required = true, dataType = "String", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "mapUrlType", value = "전달받을 kakao map url 형식", required = true, dataType = "String", paramType = "query", defaultValue = "")
    })
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/search/keyword/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DetailSearchResponseToUser detailSearchByMapId(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
            @PathVariable(value = "id") String mapId,
            @RequestParam(value = "mapUrlType") String mapUrlType
    ){
        DetailMapRequestDto requestDto = DetailMapRequestDto.builder()
                .mapId(mapId)
                .mapUrlType(API.APIDetailType.getAPIDetailType(mapUrlType))
                .build();
        return (DetailSearchResponseToUser) mapDetailSearchService.process(apiKey, requestDto);
    }

    // popular 조회 API
    @ApiOperation(value = "popular_keyword")
    @GetMapping(value = "/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    public PopularKeywordResponseToUser keywordRank(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey
    ){
        return (PopularKeywordResponseToUser) mapPopularKeywordService.process(apiKey, null);
    }
}
