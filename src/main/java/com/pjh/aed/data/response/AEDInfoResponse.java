package com.pjh.aed.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AEDInfoResponse extends Response {
    List<AED> data = new ArrayList<>();
    Page page;

    @Builder
    public AEDInfoResponse(String code, String message, List<AED> data, Page page) {
        super(code, message);
        this.data = data;
        this.page = page;
    }

    @Setter
    @Getter
    @Builder
    public static class AED{

        //설치주소
        private String buildAddress;

        //설치장소
        private String buildPlace;

        //설치기관 연락처
        private String clerkTel;

        //모델명
        private String model;

        //좌표(경도)
        private String wgs84Lat;

        //좌표(위도)
        private String wgs84Lon;

        // 요청 위치와의 거리
        private String distance;
    }

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Page{
        private Integer numOfRows;
        private Integer pageNo;
        private Integer totalCount;
    }
}
