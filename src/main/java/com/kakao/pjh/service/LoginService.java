package com.kakao.pjh.service;

import com.kakao.pjh.dao.UserDaoImpl;
import com.kakao.pjh.data.entity.User;
import com.kakao.pjh.exception.KakaoMapApiInternalServerError;
import com.kakao.pjh.exception.PasswordNotMatchException;
import com.kakao.pjh.exception.UserNotFoundException;
import com.kakao.pjh.repository.UserRepository;
import com.kakao.pjh.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@Service
@Transactional
public class LoginService {
    @Autowired
    UserDaoImpl userDao;

    Encrypt encrypt;
    public User login(User user) {
        encrypt = new EncryptHelper();
        User selectedUser = userDao.selectUser(user.getId());

        if(!encrypt.isMatch(user.getPassword(), selectedUser.getPassword()))
            throw new PasswordNotMatchException(String.format("ID[%s] Incorrect authentication credentials", user.getId()));

        String token = TokenUtil.getRandomToken();
        if(token == null)
            throw new KakaoMapApiInternalServerError();

        selectedUser.setApikey(token);
//            throw new KakaoMapApiInternalServerError();

        return selectedUser;
    }
}
