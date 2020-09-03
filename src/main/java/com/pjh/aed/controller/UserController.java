package com.pjh.aed.controller;

import com.pjh.aed.data.Result;
import com.pjh.aed.data.User;
import com.pjh.aed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public Result createUser(@RequestBody User user){
        //create user
        return new Result();
    }
}
