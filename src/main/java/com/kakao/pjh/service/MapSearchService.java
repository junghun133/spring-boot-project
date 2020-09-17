package com.kakao.pjh.service;

import com.kakao.pjh.apis.API;
import com.kakao.pjh.apis.APIFactory;
import com.kakao.pjh.config.KakaoLocalConfiguration;
import com.kakao.pjh.data.APIInfo;
import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
import com.kakao.pjh.data.dto.searchByKeyword.SearchByKeywordResponseDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MapSearchService")
@NoArgsConstructor
public class MapSearchService implements APIService {
    APIFactory apiFactory;
    KakaoLocalConfiguration kakaoLocalConfiguration;

    @Autowired
    MapSearchService(APIFactory apiFactory, KakaoLocalConfiguration kakaoLocalConfiguration){
        this.apiFactory = apiFactory;
        this.kakaoLocalConfiguration = kakaoLocalConfiguration;
    }

    @Override
    public Response process(Request request) {
        API.APIs apiType = API.APIs.KAKAO_LOCAL;
        APIInfo api = APIInfo.builder()
                .request(request)
                .response(new SearchByKeywordResponseDto())
                .apis(apiType)
                .configuration(kakaoLocalConfiguration)
                .build();
        SearchByKeywordResponseDto response = apiFactory.getAPI(apiType).APICall(api);
//TODO DB처리 DBHandler 만들고 needDbProcess

        return response;
    }
}
