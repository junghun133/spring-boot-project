package com.kakao.pjh.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter @Getter
public class MapData {
    @Id
    private Long id;
    private String place_name;
    private String category_name;
    private String category_group_code;
    private String category_group_name;
    private String phone;
    private String address_name;
    private String road_address_name;
    private Double x;
    private Double y;
    private String place_url;
    private String distance;

    private Integer hitCnt;
}
