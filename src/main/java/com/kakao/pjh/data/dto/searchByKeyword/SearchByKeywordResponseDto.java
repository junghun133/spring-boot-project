package com.kakao.pjh.data.dto.searchByKeyword;

import com.kakao.pjh.data.dto.Response;

import java.util.List;

public class SearchByKeywordResponseDto implements Response {
    private Integer total_count;
    private Integer pageable_count;
    private Boolean is_end;
    private List<SearchByKeywordResponseDocuments> documents;
}
