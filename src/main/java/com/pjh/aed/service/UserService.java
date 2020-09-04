package com.pjh.aed.service;

import com.pjh.aed.data.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User createUser(User user) {
        return new User();
    }
}
