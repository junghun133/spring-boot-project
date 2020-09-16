package com.pjh.aed.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.pjh.aed.data.dto.UserDto;
import com.pjh.aed.data.entity.DataField;
import com.pjh.aed.data.entity.EntityFilter;
import com.pjh.aed.data.entity.Result;
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
    public Resource<UserDto> createUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Fail_NotFoundUser;

        //create user
        UserDto savedUser = userDaoService.createUser(userDto);
        savedUser.setCode(code.getValue());
        savedUser.setMessage(message.getCause());
        Resource<UserDto> resource = new Resource<>(savedUser);
        ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(this.getClass()).searchUser(savedUser.getId()));
        resource.add(controllerLinkBuilder.withRel("searchUser"));

        return resource;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue searchUser(@PathVariable String id) throws UserNotFoundException {
        UserDto foundUser = userDaoService.findOne(id);
        Result.Code code = Result.Code.SUCC;
        Result.DetailMessage message = Result.DetailMessage.Fail_NotFoundUser;

        if (foundUser == null) {
            foundUser = new UserDto();
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
