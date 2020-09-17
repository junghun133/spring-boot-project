package com.kakao.pjh.dao;

import com.kakao.pjh.data.entity.User;
import com.kakao.pjh.exception.UserNotFoundException;
import com.kakao.pjh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    UserRepository userRepository;

    @Override
    public User selectUser(String userId) {
        Optional<User> userInfo = userRepository.findByUserId(userId);
        if(!userInfo.isPresent())
            throw new UserNotFoundException(String.format("ID[%s] not found", userId));
        return userInfo.orElse(null);
    }
}
