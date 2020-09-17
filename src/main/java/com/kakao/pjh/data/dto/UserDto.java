package com.kakao.pjh.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

@Getter @Setter @JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends ResponseDto {
    @NotNull
    @Length(min = 2, message = "id는 2글자 이상 입력해 주세요.")
    String id;

    @NotNull
    @Length(min = 5, message = "password 5글자 이상 입력해 주세요.")
    String password;

    String apiKey;
    String name;

    @Past
    Date createAt;
    @Past
    Date lastLoginAt;

    @Builder(builderMethodName = "userBuilder")
    public UserDto(String message, String id, String password, String apiKey, String name, Date createAt, Date lastLoginAt) {
        super(message);
        this.id = id;
        this.password = password;
        this.apiKey = apiKey;
        this.name = name;
        this.createAt = createAt;
        this.lastLoginAt = lastLoginAt;
    }
}
