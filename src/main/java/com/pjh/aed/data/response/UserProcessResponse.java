package com.pjh.aed.data.response;

import com.pjh.aed.data.Result;
import com.pjh.aed.data.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProcessResponse extends Response {
    String id;
    String name;

    public void setResult(Result.Code code, Result.DetailMessage message){
        super.code = code.getValue();
        super.message = message.getCause();
    }
}
