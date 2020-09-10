package com.pjh.aed.service;

import com.pjh.aed.data.entity.User;
import com.pjh.aed.exception.UserNotFoundException;

public interface DaoService {
    User findOne(String id) throws UserNotFoundException;
    User createUser(User user);
}
