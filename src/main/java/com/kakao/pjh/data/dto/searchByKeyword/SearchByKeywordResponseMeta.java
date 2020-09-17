package com.kakao.pjh.data.dto.searchByKeyword;

import lombok.Data;

@Data
public class SearchByKeywordResponseMeta {
    private Integer total_count;
    private Integer pageable_count;
    private Boolean is_end;
}
