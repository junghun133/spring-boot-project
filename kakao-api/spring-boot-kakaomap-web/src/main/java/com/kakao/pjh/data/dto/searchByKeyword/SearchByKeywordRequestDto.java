package com.kakao.pjh.data.dto.searchByKeyword;

import com.kakao.pjh.data.dto.Request;
import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SearchByKeywordRequestDto implements Request {
    @NotNull
    private String query;
    private String category_group_code;
    private Double x;
    private Double y;
//    @Max(value = 20000, message = "반경거리는 20000 이하로 입력해주세요")
    private Integer radius;
    private String rect;
//    @Size(min = 1, max = 45, message = "1 ~ 45 값을 입력해주세요")
    private Integer page;
//    @Size(min = 1, max = 15, message = "1 ~ 15 값을 입력해주세요")
    private Integer size;
    private String sort;
}
