package com.pjh.webflux_api.controller;

import com.pjh.webflux_api.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    //단건 return -> mono
    //복수 return -> flux
    @GetMapping("/search")
    public Mono<UserDto> search(){
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUser_id("jh");
        userDto.setUser_pwd("1234");
        return Mono.just(userDto);
    }

}
