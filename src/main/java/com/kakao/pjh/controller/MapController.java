package com.kakao.pjh.controller;

import com.kakao.pjh.data.Response;
import com.kakao.pjh.data.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/user")
public class MapController {

    @PostMapping("/login")
    public Response retrieveUsers(@Valid @RequestBody UserDto userDto){

        return null;
    }
}
