package com.pjh.aed.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.pjh.aed.data.DataField;
import com.pjh.aed.data.EntityFilter;
import com.pjh.aed.data.Result;
import com.pjh.aed.data.dto.UserBindData;
import com.pjh.aed.data.entity.User;
import com.pjh.aed.exception.UserNotFoundException;
import com.pjh.aed.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    UserDaoService userDaoService;

    @Autowired
    EntityFilter entityFilter;

    @PostMapping("/create")
    public EntityModel<User> createUser(@RequestBody UserBindData userBindData) throws UserNotFoundException {
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Fail_NotFoundUser;

        User userDto = new User();
        userDto.setName(userBindData.getName());
        userDto.setId(userBindData.getId());
        //create user
        userDaoService.createUser(userDto);

        User savedUser = new User();
//        savedUser.setCode(code.getValue());
//        savedUser.setMessage(message.getCause());
        EntityModel<User> resource = new EntityModel<>(savedUser);
        WebMvcLinkBuilder controllerLinkBuilder = linkTo(methodOn(this.getClass()).searchUser(savedUser.getId()));
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
