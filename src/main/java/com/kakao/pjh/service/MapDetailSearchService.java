package com.kakao.pjh.service;

import com.kakao.pjh.config.KakaoMapAPIsConfiguration;
import com.kakao.pjh.dao.MapSearchDaoImpl;
import com.kakao.pjh.dao.UserDaoImpl;
import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
import com.kakao.pjh.data.dto.detail.DetailMapRequestDto;
import com.kakao.pjh.data.dto.detail.DetailSearchResponseToUser;
import com.kakao.pjh.data.entity.Map;
import com.kakao.pjh.exception.MapDataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MapDetailSearchService")
public class MapDetailSearchService implements APIService{

    KakaoMapAPIsConfiguration kakaoMapAPIsConfiguration;
    UserDaoImpl userDao;
    MapSearchDaoImpl mapSearchDao;

    @Autowired
    public MapDetailSearchService(KakaoMapAPIsConfiguration kakaoMapAPIsConfiguration, UserDaoImpl userDao, MapSearchDaoImpl mapSearchDao) {
        this.kakaoMapAPIsConfiguration = kakaoMapAPIsConfiguration;
        this.userDao = userDao;
        this.mapSearchDao = mapSearchDao;
    }

    @Override
    public Response process(String apiKey, Request request) {
        userDao.apiKeyValidation(apiKey);
        DetailMapRequestDto requestDto = (DetailMapRequestDto) request;

        Map map = mapSearchDao.selectMapData(Long.parseLong(requestDto.getMapId()));
        if(map == null)
            throw new MapDataNotFoundException();

        return null;
    }
}
