package com.pjh.aed.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.pjh.aed.dao.AuthDao;
import com.pjh.aed.dao.UserDao;
import com.pjh.aed.data.DataField;
import com.pjh.aed.data.EntityFilter;
import com.pjh.aed.data.Result;
import com.pjh.aed.data.domain.User;
import com.pjh.aed.data.domain.UserAuthentication;
import com.pjh.aed.data.request.UserRequestData;
import com.pjh.aed.data.response.UserInfoResponse;
import com.pjh.aed.jwt.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/create/user")
    public EntityModel<UserInfoResponse> createUser(@RequestBody @Valid UserRequestData userRequestData) throws JsonProcessingException {
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Success;

        User userDto = new User();
        userDto.setName(userRequestData.getName());
        userDto.setId(userRequestData.getId());
        userDto.setPassword(userRequestData.getPassword());
        //create user
        userDao.createUser(userDto);

        UserInfoResponse savedUser = UserInfoResponse.userProcessResponseBuilder()
                .id(userDto.getId())
                .name(userDto.getName())
                .build();
        savedUser.setResult(code, message);

        EntityModel<UserInfoResponse> resource = new EntityModel<UserInfoResponse>(savedUser);

        WebMvcLinkBuilder searchUserLinkBuilder = linkTo(methodOn(this.getClass()).searchUser(savedUser.getId()));
        WebMvcLinkBuilder createTokenLinkBuilder = linkTo(methodOn(this.getClass()).createAPIKey(null));
        resource.add(searchUserLinkBuilder.withRel("searchUser"));
        resource.add(createTokenLinkBuilder.withRel("createToken"));

        return resource;
    }

    @GetMapping("/search/{id}")
    public MappingJacksonValue searchUser(@PathVariable String id) {
        User foundUser = userDao.isUser(id);
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Success;

        if (foundUser == null) {
            //throw new UserNotFoundException(String.format("[ID %d] not found", id));
            code = Result.Code.FAIL;
            message = Result.DetailMessage.Fail_NotFoundUser;
        }
        FilterProvider filters =  entityFilter.filter("UserFilter", DataField.User.id.name(), DataField.User.name.name());

        UserInfoResponse userInfoResponse = UserInfoResponse.userProcessResponseBuilder()
                .id(foundUser.getId())
                .name(foundUser.getName())
                .build();
        userInfoResponse.setResult(code, message);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userInfoResponse);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @PostMapping("/create/token")
    public EntityModel<UserInfoResponse> createAPIKey(@RequestBody UserRequestData userRequestData) {
        User foundUser = userDao.loginUser(userRequestData.getId(), userRequestData.getPassword());
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Success;

        if (foundUser == null) {
            code = Result.Code.FAIL;
            message = Result.DetailMessage.Fail_NotFoundUser;

            UserInfoResponse userInfoResponse = new UserInfoResponse();
            userInfoResponse.setResult(code, message);
            return new EntityModel<>(userInfoResponse);
        }
        String token = jwtService.create(userRequestData.getId());
        authDao.createToken(token, foundUser);

        User user = userDao.loginUser(userRequestData.getId(), userRequestData.getPassword());

        UserInfoResponse userInfoResponse = UserInfoResponse.userProcessResponseBuilder()
                .id(foundUser.getId())
                .name(foundUser.getName())
                .build();
        userInfoResponse.setResult(code, message);

        List<UserAuthentication> userAuthenticationList = user.getUserAuthenticationList();
        List<String> tokens = new ArrayList<>();
        for (UserAuthentication ua : userAuthenticationList) {
            tokens.add(ua.getToken());
        }

        userInfoResponse.setToken(tokens);
        return new EntityModel<>(userInfoResponse);
    }
}
