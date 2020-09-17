package com.kakao.pjh.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter @JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends CommonDto {
    @NotNull
    String id;

    @NotNull
    String password;

    String apiKey;
    String name;

    Date createAt;
    Date lastLoginAt;

    @Builder(builderMethodName = "userBuilder")
    public UserDto(int result, String message, String id, String password, String apiKey, String name, Date createAt, Date lastLoginAt) {
        super(result, message);
        this.id = id;
        this.password = password;
        this.apiKey = apiKey;
        this.name = name;
        this.createAt = createAt;
        this.lastLoginAt = lastLoginAt;
    }
}
