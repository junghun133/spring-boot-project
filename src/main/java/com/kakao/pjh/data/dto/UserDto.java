package com.kakao.pjh.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class UserDto {
    Long code;
    String id;

    @JsonIgnore
    String password;

    Date createAt;
    Date lastLoginAt;
}
