package com.pjh.aed.service;

import com.pjh.aed.data.dto.UserBindData;
import com.pjh.aed.data.dto.res.UserProcessResponse;
import com.pjh.aed.data.entity.User;
import com.pjh.aed.exception.UserNotFoundException;

public interface DaoService {
    void createUser(User user);
    UserProcessResponse findOne(String id) throws UserNotFoundException;
}
