package com.kakao.pjh.data.dto.searchByKeyword;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchByKeywordResponseMetaFromKakaoAPI {
    private Integer total_count;
    private Integer pageable_count;
    private Boolean is_end;
}
