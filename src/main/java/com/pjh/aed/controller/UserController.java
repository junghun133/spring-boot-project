package com.pjh.aed.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.pjh.aed.data.dto.UserBindData;
import com.pjh.aed.data.entity.*;
import com.pjh.aed.data.entity.User;
import com.pjh.aed.exception.UserNotFoundException;
import com.pjh.aed.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    UserDaoService userDaoService;

    @Autowired
    EntityFilter entityFilter;

    @PostMapping("/create")
    public Resource<User> createUser(@RequestBody User user) throws UserNotFoundException {
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Fail_NotFoundUser;

        UserBindData userBindData = new UserBindData();
        userBindData.setName(user.getName());
        userBindData.setId(user.getId());
        //create user
        userDaoService.createUser(userBindData);

        User savedUser = new User();
        savedUser.setCode(code.getValue());
        savedUser.setMessage(message.getCause());
        Resource<User> resource = new Resource<>(savedUser);
        ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(this.getClass()).searchUser(user.getId()));
        resource.add(controllerLinkBuilder.withRel("searchUser"));

        return resource;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue searchUser(@PathVariable String id) throws UserNotFoundException {
        UserBindData foundUser = userDaoService.findOne(id);
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Fail_NotFoundUser;

        if (foundUser == null) {
            //throw new UserNotFoundException(String.format("[ID %d] not found", id));
            foundUser = new UserBindData();
            code = Result.Code.FAIL;
            message = Result.DetailMessage.Fail_NotFoundUser;
        }
        FilterProvider filters =  entityFilter.filter("UserFilter", DataField.User.id.name(), DataField.User.name.name());

        foundUser.setCode(code.getValue());
        foundUser.setMessage(message.getCause());
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(foundUser);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
