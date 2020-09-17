package com.kakao.pjh.service;

import com.kakao.pjh.apis.API;
import com.kakao.pjh.apis.APIFactory;
import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
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

    public Response search(Request request, API.APIs apiType) {
        //인기순위 처리 TODO
        return apiFactory.getAPI(apiType).APICall(request, API.APIDetailType.KAKAO_MAP );
    }
}
