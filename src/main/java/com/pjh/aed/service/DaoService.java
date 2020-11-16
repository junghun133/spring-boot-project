package com.pjh.aed.service;

import com.pjh.aed.data.dto.UserBindData;
import com.pjh.aed.exception.UserNotFoundException;

public interface DaoService {
    UserBindData findOne(String id) throws UserNotFoundException;
    UserBindData createUser(UserBindData user);
}
