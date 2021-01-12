package com.kakao.pjh.apis;

import com.kakao.pjh.exception.KakaoApiInternalServerError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIFactory { //다양한 API가 추가될시 Factory component로 추가
    KakaoBizMessageAPI kakaoBizMessageAPI;
    KakaoLocalAPI kakaoLocalAPI;

    @Autowired
    public APIFactory(KakaoBizMessageAPI kakaoBizMessageAPI, KakaoLocalAPI kakaoLocalAPI){
        this.kakaoBizMessageAPI = kakaoBizMessageAPI;
        this.kakaoLocalAPI = kakaoLocalAPI;
    }

    public API getAPI(API.APIs apiType){
        switch (apiType){
            case KAKAO_LOCAL:
                return kakaoLocalAPI;

/*            case KAKAO_MAP:
                return kakaoBizMessageAPI;
              case ...
              */
            default:
                throw new KakaoApiInternalServerError();
        }
    }
}
