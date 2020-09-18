package com.kakao.pjh.service;

import com.kakao.pjh.apis.API;
import com.kakao.pjh.apis.APIFactory;
import com.kakao.pjh.config.KakaoLocalConfiguration;
import com.kakao.pjh.dao.MapSearchDaoImpl;
import com.kakao.pjh.dao.UserDaoImpl;
import com.kakao.pjh.data.APIInfo;
import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
import com.kakao.pjh.data.dto.searchByKeyword.*;
import com.kakao.pjh.data.entity.Map;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("MapSearchService")
@NoArgsConstructor
public class MapSearchService implements APIService {
    APIFactory apiFactory;
    KakaoLocalConfiguration kakaoLocalConfiguration;
    UserDaoImpl userDao;
    MapSearchDaoImpl mapSearchDao;

    @Autowired
    public MapSearchService(APIFactory apiFactory, KakaoLocalConfiguration kakaoLocalConfiguration, UserDaoImpl userDao, MapSearchDaoImpl mapSearchDao) {
        this.apiFactory = apiFactory;
        this.kakaoLocalConfiguration = kakaoLocalConfiguration;
        this.userDao = userDao;
        this.mapSearchDao = mapSearchDao;
    }

    @Override
    public Response process(String apiKey, Request request) {
        userDao.apiKeyValidation(apiKey);
        API.APIs apiType = API.APIs.KAKAO_LOCAL;
        APIInfo api = APIInfo.builder()
                .request(request)
                .response(new SearchByKeywordResponseDtoFromKakaoAPI())
                .apis(apiType)
                .configuration(kakaoLocalConfiguration)
                .build();
        SearchByKeywordResponseDtoFromKakaoAPI response = apiFactory.getAPI(apiType).APICall(api);

        // DB process
        SearchByKeywordResponseMetaFromKakaoAPI meta = response.getMeta();
        List<SearchByKeywordDocumentsFromKakaoAPI> documents = response.getDocuments();
        // set response meta
        SearchByKeywordResponseToUser responseToUser = new SearchByKeywordResponseToUser();
        responseToUser.setMeta(SearchByKeywordResponseMetaToUser.builder()
                .is_end(meta.getIs_end())
                .pageable_count(meta.getPageable_count())
                .total_count(meta.getTotal_count())
                .build());

        //set response document
        List<SearchByKeywordResponseDocumentToUser> documentToUsers = new ArrayList<>();
        for (SearchByKeywordDocumentsFromKakaoAPI document : documents) {
            documentToUsers.add(SearchByKeywordResponseDocumentToUser.builder()
                    .id(document.getId())
                    .category_name(document.getCategory_name())
                    .place_name(document.getPlace_name())
                    .road_address_name(document.getRoad_address_name())
                    .build());
        }
        responseToUser.setDocuments(documentToUsers);
        //set entity
        for (SearchByKeywordDocumentsFromKakaoAPI document : documents) {
            mapSearchDao.mergeIntoMapRowData(Map.builder()
                    .mapId(Long.parseLong(document.getId()))
                    .place_name(document.getPlace_name())
                    .category_name(document.getCategory_name())
                    .category_group_name(document.getCategory_group_name())
                    .category_group_code(document.getCategory_group_code())
                    .phone(document.getPhone())
                    .address_name(document.getAddress_name())
                    .road_address_name(document.getRoad_address_name())
                    .place_url(document.getPlace_url())
                    .build());
        }
        return responseToUser;
    }
}
