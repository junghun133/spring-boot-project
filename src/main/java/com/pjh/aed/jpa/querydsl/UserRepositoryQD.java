package com.pjh.aed.jpa.querydsl;

import com.pjh.aed.data.entity.User;

import java.util.List;

public interface UserRepositoryQD {
    List<User> userList(User user);
}
