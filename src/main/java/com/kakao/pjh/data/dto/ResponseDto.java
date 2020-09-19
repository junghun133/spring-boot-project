package com.kakao.pjh.data.dto;

import com.kakao.pjh.data.ResultComponent;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ResponseDto implements Response {
    String message;

    @Override
    public void setMessage(ResultComponent.Result result){
        if(messageDisplay())
            this.message = result.getMessage();
    }

    protected abstract boolean messageDisplay();
}
