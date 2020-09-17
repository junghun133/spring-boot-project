package com.kakao.pjh.data.dto;

import com.kakao.pjh.data.ResultComponent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class ResponseDto implements Response {
    String message;

    public ResponseDto(String message){
        this.message = message;
    }
}
