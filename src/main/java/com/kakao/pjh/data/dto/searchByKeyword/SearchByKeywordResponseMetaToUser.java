package com.kakao.pjh.data.dto.searchByKeyword;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchByKeywordResponseMetaToUser {
    private Integer total_count;
    private Integer pageable_count;
    private Boolean is_end;
}
