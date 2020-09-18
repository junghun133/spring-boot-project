package com.kakao.pjh.data.dto.searchByKeyword;

import com.kakao.pjh.data.dto.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchByKeywordResponseToUser extends ResponseDto {
    SearchByKeywordResponseMetaToUser meta;
    List<SearchByKeywordResponseDocumentToUser> documents;
}
