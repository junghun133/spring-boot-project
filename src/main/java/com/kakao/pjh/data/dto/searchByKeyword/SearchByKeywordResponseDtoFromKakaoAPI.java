package com.kakao.pjh.data.dto.searchByKeyword;

import com.kakao.pjh.data.dto.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class SearchByKeywordResponseDtoFromKakaoAPI extends ResponseDto {
    private SearchByKeywordResponseMetaFromKakaoAPI meta;
    private List<SearchByKeywordDocumentsFromKakaoAPI> documents;
}
