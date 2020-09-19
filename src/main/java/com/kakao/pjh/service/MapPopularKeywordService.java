package com.kakao.pjh.service;

import com.kakao.pjh.dao.MapSearchDaoImpl;
import com.kakao.pjh.dao.UserDaoImpl;
import com.kakao.pjh.data.ResultComponent;
import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
import com.kakao.pjh.data.dto.popular.PopularKeywordResponseToUser;
import com.kakao.pjh.data.entity.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("MapPopularKeywordService")
public class MapPopularKeywordService implements APIService{
    MapSearchDaoImpl mapSearchDao;
    UserDaoImpl userDao;

    @Autowired
    public MapPopularKeywordService(MapSearchDaoImpl mapSearchDao, UserDaoImpl userDao) {
        this.mapSearchDao = mapSearchDao;
        this.userDao = userDao;
    }

    @Override
    public Response process(String apiKey, Request request) {
        userDao.apiKeyValidation(apiKey);

        List<Keyword> topKeywords = mapSearchDao.selectPopularKeyword();

        List<String> keywords = new ArrayList<>();
        topKeywords.stream().forEach( t -> keywords.add(t.getKeyword()));
        PopularKeywordResponseToUser responseToUser = PopularKeywordResponseToUser.builder()
                .popularKeywords(keywords)
                .build();

        responseToUser.setMessage(ResultComponent.Result.SUCC);
        return responseToUser;
    }
}
