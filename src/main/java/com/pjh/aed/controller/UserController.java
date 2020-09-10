package com.pjh.aed.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pjh.aed.data.entity.DataField;
import com.pjh.aed.data.entity.EntityFilter;
import com.pjh.aed.data.entity.Result;
import com.pjh.aed.data.entity.User;
import com.pjh.aed.exception.UserNotFoundException;
import com.pjh.aed.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

        //create user
        User savedUser = userDaoService.createUser(user);
        savedUser.setCode(code.getValue());
        savedUser.setMessage(message.getCause());
        Resource<User> resource = new Resource<>(savedUser);
        ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(this.getClass()).searchUser(user.getId()));
        resource.add(controllerLinkBuilder.withRel("searchUser"));

        return resource;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue searchUser(@PathVariable String id) throws UserNotFoundException {
        User foundUser = userDaoService.findOne(id);
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Fail_NotFoundUser;

        if (foundUser == null) {
            foundUser = new User();
            //throw new UserNotFoundException(String.format("[ID %d] not found", id));
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
