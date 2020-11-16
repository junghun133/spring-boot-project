package com.pjh.aed.data.dto;

import com.pjh.aed.data.response.Response;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class UserDto extends Response {
    String id;
    String name;
}
