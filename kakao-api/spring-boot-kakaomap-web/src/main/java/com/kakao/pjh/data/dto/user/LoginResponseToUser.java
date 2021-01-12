package com.kakao.pjh.data.dto.user;

import com.kakao.pjh.data.dto.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
public class LoginResponseToUser extends ResponseDto {
    String id;
    String apiKey;
    String name;
    Date createAt;
    Date lastLoginAt;

    @Override
    protected boolean messageDisplay() {
        return true;
    }
}
