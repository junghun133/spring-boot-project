package com.pjh.aed.data.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class UserBindData {

    @NotNull
    String id;
    String name;
    @NotNull
    String password;

    @Builder(builderMethodName = "userBindDataBuilder")
    public UserBindData(String id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
