package com.kakao.pjh.data.dto.popular;

import com.kakao.pjh.data.dto.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PopularKeywordResponseToUser extends ResponseDto {
    List<String> popularKeywords;

    @Override
    protected boolean messageDisplay() {
        return true;
    }
}
