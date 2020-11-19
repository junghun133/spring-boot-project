package com.pjh.aed.data.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class UserBindData {
    String id;
    String name;
    String password;
}
