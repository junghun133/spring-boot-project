package com.kakao.pjh.data.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Keyword {
    @Id
    @GeneratedValue
    private Long id;
    private String keyword;

    @Column(columnDefinition = "INTEGER DEFAULT 1", nullable = false)
    private Integer hitCnt;
}
