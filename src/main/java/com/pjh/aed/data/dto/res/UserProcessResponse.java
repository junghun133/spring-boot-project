package com.pjh.aed.data.dto.res;

import com.pjh.aed.data.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProcessResponse extends Response {
    String id;
    String name;
}
