package com.kakao.pjh.data.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchByKeywordRequestDto {
    private String query;
    private String category_group_code;
    private Double x;
    private Double y;
    private Integer radius;
    private String rect;
    private Integer page;
    private Integer size;
    private String sort;
}
