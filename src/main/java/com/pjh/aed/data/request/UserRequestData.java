package com.pjh.aed.data.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class UserRequestData extends CommonRequestData {

    @NotNull
    String id;
    String name;
    @NotNull
    String password;

    @Builder(builderMethodName = "userBindDataBuilder")
    public UserRequestData(String id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
