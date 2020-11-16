package com.pjh.aed.service;

import com.pjh.aed.data.dto.UserDto;
import com.pjh.aed.exception.UserNotFoundException;

public interface DaoService {
    UserDto findOne(String id) throws UserNotFoundException;
    UserDto createUser(UserDto user);
}
