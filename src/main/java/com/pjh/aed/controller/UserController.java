package com.pjh.aed.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.pjh.aed.data.DataField;
import com.pjh.aed.data.EntityFilter;
import com.pjh.aed.data.Result;
import com.pjh.aed.data.dto.UserBindData;
import com.pjh.aed.data.entity.User;
import com.pjh.aed.data.entity.UserAuthentication;
import com.pjh.aed.data.response.UserProcessResponse;
import com.pjh.aed.exception.UserNotFoundException;
import com.pjh.aed.jwt.JWTService;
import com.pjh.aed.dao.AuthDao;
import com.pjh.aed.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    UserDao userDao;

    @Autowired
    AuthDao authDao;

    @Autowired
    EntityFilter entityFilter;

    @Autowired
    JWTService jwtService;

    @PostMapping("/create/user")
    public EntityModel<UserProcessResponse> createUser(@RequestBody UserBindData userBindData) throws UserNotFoundException {
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Success;

        User userDto = new User();
        userDto.setName(userBindData.getName());
        userDto.setId(userBindData.getId());
        userDto.setPassword(userBindData.getPassword());
        //create user
        userDao.createUser(userDto);

        UserProcessResponse savedUser = new UserProcessResponse();
        savedUser.setCode(code.getValue());
        savedUser.setMessage(message.getCause());
        savedUser.setId(userDto.getId());
        savedUser.setName(userDto.getName());
        EntityModel<UserProcessResponse> resource = new EntityModel<UserProcessResponse>(savedUser);

        WebMvcLinkBuilder searchUserLinkBuilder = linkTo(methodOn(this.getClass()).searchUser(savedUser.getId()));
        WebMvcLinkBuilder createTokenLinkBuilder = linkTo(methodOn(this.getClass()).createAPIKey(null));
        resource.add(searchUserLinkBuilder.withRel("searchUser"));
        resource.add(createTokenLinkBuilder.withRel("createToken"));

        return resource;
    }

    @GetMapping("/search/{id}")
    public MappingJacksonValue searchUser(@PathVariable String id) throws UserNotFoundException {
        User foundUser = userDao.isUser(id);
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Success;

        if (foundUser == null) {
            //throw new UserNotFoundException(String.format("[ID %d] not found", id));
            code = Result.Code.FAIL;
            message = Result.DetailMessage.Fail_NotFoundUser;
        }
        FilterProvider filters =  entityFilter.filter("UserFilter", DataField.User.id.name(), DataField.User.name.name());

        UserProcessResponse userProcessResponse = new UserProcessResponse();
        userProcessResponse.setResult(code, message);
        userProcessResponse.setId(foundUser.getId());
        userProcessResponse.setName(foundUser.getName());
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userProcessResponse);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @PostMapping("/create/token")
    public EntityModel<UserProcessResponse> createAPIKey(@RequestBody UserBindData userBindData) throws UserNotFoundException {
        User foundUser = userDao.loginUser(userBindData.getId(), userBindData.getPassword());
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Success;

        if (foundUser == null) {
            code = Result.Code.FAIL;
            message = Result.DetailMessage.Fail_NotFoundUser;

            UserProcessResponse userProcessResponse = new UserProcessResponse();
            userProcessResponse.setResult(code, message);
            return new EntityModel<>(userProcessResponse);
        }
        String token = jwtService.create(userBindData.getId());
        authDao.createToken(token, foundUser);

        User user = userDao.loginUser(userBindData.getId(), userBindData.getPassword());

        UserProcessResponse userProcessResponse = new UserProcessResponse();
        userProcessResponse.setResult(code, message);
        userProcessResponse.setId(foundUser.getId());
        userProcessResponse.setName(foundUser.getName());

        List<UserAuthentication> userAuthenticationList = user.getUserAuthenticationList();
        List<String> tokens = new ArrayList<>();
        for (UserAuthentication ua : userAuthenticationList) {
            tokens.add(ua.getToken());
        }

        userProcessResponse.setToken(tokens);
        return new EntityModel<>(userProcessResponse);
    }
}
