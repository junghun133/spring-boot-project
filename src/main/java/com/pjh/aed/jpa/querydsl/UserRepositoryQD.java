package com.pjh.aed.jpa.querydsl;

import com.pjh.aed.data.dto.UserBindData;

import java.util.List;

public interface UserRepositoryQD {
    List<UserBindData> userList(UserBindData userBindData);
}
