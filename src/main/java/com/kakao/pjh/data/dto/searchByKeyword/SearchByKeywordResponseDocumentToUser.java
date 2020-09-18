package com.kakao.pjh.data.dto.searchByKeyword;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchByKeywordResponseDocumentToUser {
    String id;
    String place_name;
    String category_name;
    String road_address_name;
}
