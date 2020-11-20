package com.pjh.aed.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pjh.aed.data.Result;
import com.pjh.aed.data.entity.UserAuthentication;
import lombok.*;

import java.util.List;

@Getter
@Setter
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

    @Builder(builderMethodName = "userProcessResponseBuilder")
    public UserProcessResponse(String id, String name, List<String> token){
        this.id = id;
        this.name = name;
        this.token = token;
    }
}
