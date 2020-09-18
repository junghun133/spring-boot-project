package com.kakao.pjh.data.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Map {
    @Id
    @GeneratedValue
    private Long id;

    private Long mapId;
    private String place_name;
    private String category_name;
    private String category_group_code;
    private String category_group_name;
    private String phone;
    private String address_name;
    private String road_address_name;
    private String place_url;

    @Column(columnDefinition = "INTEGER DEFAULT 1", nullable = false)
    private Integer hitCnt;
}
