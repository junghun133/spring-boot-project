package com.kakao.pjh.data.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kakao.pjh.data.dto.Request;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter @Setter @JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Request {
    @NotNull
//    @Length(min = 2, message = "id는 2글자 이상 입력해 주세요.")
    String id;

    @NotNull
//    @Length(min = 5, message = "password 5글자 이상 입력해 주세요.")
    String password;

    String apiKey;
    String name;

    @Past
    Date createAt;
    @Past
    Date lastLoginAt;

    @Builder(builderMethodName = "userBuilder")
    public UserDto(String id, String password, String apiKey, String name, Date createAt, Date lastLoginAt) {
        this.id = id;
        this.password = password;
        this.apiKey = apiKey;
        this.name = name;
        this.createAt = createAt;
        this.lastLoginAt = lastLoginAt;
    }

    @JsonCreator
    public UserDto(String id, String password){
        this.id = id;
        this.password = password;
    }
}
