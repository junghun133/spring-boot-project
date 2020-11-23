package com.pjh.aed.jpa.querydsl;

import com.pjh.aed.data.domain.User;

import java.util.List;

public interface UserRepositoryQD {
    List<User> selectAllUserList(User user);
    List<User> selectUserId(String userId);
    List<User> selectUserIdAndPassword(String userId, String password);
}