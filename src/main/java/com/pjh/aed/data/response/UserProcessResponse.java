package com.pjh.aed.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pjh.aed.data.Result;
import com.pjh.aed.data.entity.UserAuthentication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProcessResponse extends Response {
    String id;
    String name;

    List<String> token;

    public void setResult(Result.Code code, Result.DetailMessage message){
        super.code = code.getValue();
        super.message = message.getCause();
    }
}
