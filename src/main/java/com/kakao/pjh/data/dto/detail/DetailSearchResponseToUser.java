package com.kakao.pjh.data.dto.detail;

import com.kakao.pjh.data.dto.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailSearchResponseToUser extends ResponseDto {
    private String id;
    private String place_name;
    private String category_name;
    private String category_group_code;
    private String category_group_name;
    private String phone;
    private String address_name;
    private String road_address_name;
    private String place_url;

    @Override
    protected boolean messageDisplay() {
        return true;
    }
}
