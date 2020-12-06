package com.pjh.webflux_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserDto {
    private long id;
    private String user_id;
    private String user_pwd;
}
