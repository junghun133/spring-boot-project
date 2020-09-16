package com.kakao.pjh.service;

import com.kakao.pjh.data.entity.User;
import com.kakao.pjh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    Encrypt encrypt;
    public boolean login(User user) {
        encrypt = new EncryptHelper();
        Optional<User> userInfo = userRepository.findByUserId(user.getId());
        return userInfo.isPresent() && encrypt.isMatch(user.getPassword(), userInfo.get().getPassword());
    }
}
