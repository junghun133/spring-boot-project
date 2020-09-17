package com.kakao.pjh.controller;

import com.kakao.pjh.data.ResultComponent;
import com.kakao.pjh.data.dto.UserDto;
import com.kakao.pjh.data.entity.User;
import com.kakao.pjh.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/v1/user")
public class UserController {
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public UserDto loginUsers(@RequestBody @Valid UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());

        User loginUser = loginService.login(user);
        ResultComponent.Result result = ResultComponent.Result.SUCC;
        return UserDto.userBuilder()
                .message(result.getMessage())
                .apiKey(loginUser.getApikey())
                .name(loginUser.getName())
                .createAt(loginUser.getCreateAt())
                .lastLoginAt(loginUser.getLastLoginAt())
                .build();
    }
}
