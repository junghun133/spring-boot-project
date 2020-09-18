package com.kakao.pjh.data.dto.detail;

import com.kakao.pjh.apis.API;
import com.kakao.pjh.data.dto.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetailMapRequestDto implements Request{
    private String mapId;
    private API.APIDetailType mapUrlType;
}
