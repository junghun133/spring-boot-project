package com.pjh.aed.dao;

import com.pjh.aed.data.entity.User;
import com.pjh.aed.data.entity.UserAuthentication;
import com.pjh.aed.jpa.UserAuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthDao {
    private UserAuthRepository userAuthRepository;

    public void createToken(String token, User user) {
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setUser(user);
        userAuthentication.setToken(token);
        userAuthentication.setUse("Y");
        userAuthRepository.save(userAuthentication);
    }
}
