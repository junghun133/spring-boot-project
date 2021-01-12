package com.kakao.pjh.controller;

import com.kakao.pjh.data.dto.user.LoginResponseToUser;
import com.kakao.pjh.data.dto.user.UserDto;
import com.kakao.pjh.service.LoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/v1/user")
public class UserController {
    @Autowired
    LoginService loginService;

    @ApiOperation(value = "login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "아이디", required = true, dataType = "string", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "password", value = "비밀번호", required = true, dataType = "string", paramType = "query", defaultValue = ""),
    })
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public LoginResponseToUser loginUsers(@RequestBody @Valid UserDto userDto){
        return (LoginResponseToUser) loginService.process(null, userDto);
    }
}
