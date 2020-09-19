package com.kakao.pjh.controller;

import com.kakao.pjh.data.dto.user.LoginResponseToUser;
import com.kakao.pjh.data.dto.user.UserDto;
import com.kakao.pjh.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/v1/user")
public class UserController {
    @Autowired
    LoginService loginService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public LoginResponseToUser loginUsers(@RequestBody @Valid UserDto userDto){
        return (LoginResponseToUser) loginService.process(null, userDto);
    }
}
