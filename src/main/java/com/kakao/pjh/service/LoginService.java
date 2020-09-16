package com.kakao.pjh.service;

import com.kakao.pjh.data.entity.User;
import com.kakao.pjh.exception.PasswordNotMatchException;
import com.kakao.pjh.exception.UserNotFoundException;
import com.kakao.pjh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    Encrypt encrypt;
    public void login(User user) {
        encrypt = new EncryptHelper();
        Optional<User> userInfo = userRepository.findByUserId(user.getId());
        if(!userInfo.isPresent())
            throw new UserNotFoundException(String.format("ID[%s] not found", user.getId()));


        if(!encrypt.isMatch(user.getPassword(), userInfo.get().getPassword()))
            throw new PasswordNotMatchException(String.format("ID[%s] Incorrect authentication credentials", user.getId()));
    }
}
