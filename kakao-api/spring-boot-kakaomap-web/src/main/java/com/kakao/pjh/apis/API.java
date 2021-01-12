package com.kakao.pjh.apis;

import com.kakao.pjh.data.APIInfo;
import com.kakao.pjh.data.dto.Response;
import com.kakao.pjh.exception.InvalidAPiDetailType;

public interface API {
    enum APIs{
        KAKAO_LOCAL, KAKAO_BIZMESSAGE //....TODO
    }
    enum APIDetailType{
        KAKAO_MAP("map"),
        KAKAO_ROAD("to"),
        KAKAO_ROADVIEW("roadview");

        String value;
        APIDetailType(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }

        public static APIDetailType getAPIDetailType(String str){
            for(APIDetailType a : APIDetailType.values()){
                if(a.value.contentEquals(str))
                    return a;
            }
            throw new InvalidAPiDetailType();
        }
    }
    <R extends Response> R APICall(APIInfo apiInfo);
}
