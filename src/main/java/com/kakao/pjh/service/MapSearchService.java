package com.kakao.pjh.service;

import com.kakao.pjh.apis.API;
import com.kakao.pjh.apis.APIFactory;
import com.kakao.pjh.config.KakaoLocalConfiguration;
import com.kakao.pjh.config.KakaoMapAPIsConfiguration;
import com.kakao.pjh.data.dto.Response;
import com.kakao.pjh.data.dto.SearchByKeywordRequestDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class MapSearchService {
    @Autowired
    APIFactory apiFactory;

    @Autowired
    KakaoLocalConfiguration kakaoLocalConfiguration;
    @Autowired
    KakaoMapAPIsConfiguration apiMapConfiguration;

    public Response search(SearchByKeywordRequestDto searchByKeywordRequestDto, API.APIs apiType) {
        //인기순위
        return apiFactory.getAPI(apiType).APICall(searchByKeywordRequestDto, API.APIDetailType.KAKAO_MAP );
    }
}
