package com.kakao.pjh.controller;

import com.kakao.pjh.data.ResultComponent;
import com.kakao.pjh.data.dto.UserDto;
import com.kakao.pjh.data.entity.User;
import com.kakao.pjh.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/user")
public class MapController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public UserDto retrieveUsers(@Valid @RequestBody UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());

        loginService.login(user);
        ResultComponent.Result result = ResultComponent.Result.SUCC;
        return UserDto.userBuilder()
                .result(result.getCode())
                .message(result.getMessage())
                .build();
    }
}
