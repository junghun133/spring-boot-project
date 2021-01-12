package com.kakao.pjh.dao;

import com.kakao.pjh.data.entity.User;

public interface UserDao {
    User selectUser(String userId);
    void apiKeyValidation(String apiKey);
}
