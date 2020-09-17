package com.kakao.pjh.data.dto.searchByKeyword;

import com.kakao.pjh.data.dto.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class SearchByKeywordResponseDto extends ResponseDto {
    private SearchByKeywordResponseMeta meta;
    private List<SearchByKeywordResponseDocuments> documents;
}
