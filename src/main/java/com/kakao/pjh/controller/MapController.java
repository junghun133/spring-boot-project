package com.kakao.pjh.controller;

import com.kakao.pjh.apis.API;
import com.kakao.pjh.data.dto.detail.DetailMapRequestDto;
import com.kakao.pjh.data.dto.detail.DetailSearchResponseToUser;
import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordRequestDto;
import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordResponseDocumentToUser;
import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordResponseToUser;
import com.kakao.pjh.service.MapDetailSearchService;
import com.kakao.pjh.service.MapSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/v1/map")
public class MapController {
    @Autowired
    MapSearchService mapSearchService;
    @Autowired
    MapDetailSearchService mapDetailSearchService;

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

        SearchByKeywordResponseToUser response = (SearchByKeywordResponseToUser) mapSearchService.process(apiKey, requestDto);

        List<SearchByKeywordResponseDocumentToUser> documents = response.getDocuments();
        if(documents.size() > 0){
            for (SearchByKeywordResponseDocumentToUser document : documents) {
                API.APIDetailType apiDetailType = API.APIDetailType.KAKAO_MAP;

                URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/detail/{id}")
                        .queryParam("mapUrlType", apiDetailType.getValue())
                        .buildAndExpand(document.getId())
                        .toUri();

                document.setDetailUri(location.toString());
            }
        }
        return response;
    }

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

}
