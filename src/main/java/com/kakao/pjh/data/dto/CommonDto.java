package com.kakao.pjh.data.dto;

import com.kakao.pjh.data.ResultComponent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class CommonDto implements ResultComponent {
    int result;
    String message;

    public CommonDto(int result, String message){
        this.result = result;
        this.message = message;
    }
}
