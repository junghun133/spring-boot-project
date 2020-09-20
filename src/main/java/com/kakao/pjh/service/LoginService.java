package com.kakao.pjh.service;

import com.kakao.pjh.dao.UserDaoImpl;
import com.kakao.pjh.data.ResultComponent;
import com.kakao.pjh.data.dto.Request;
import com.kakao.pjh.data.dto.Response;
import com.kakao.pjh.data.dto.user.LoginResponseToUser;
import com.kakao.pjh.data.dto.user.UserDto;
import com.kakao.pjh.data.entity.User;
import com.kakao.pjh.exception.KakaoApiInternalServerError;
import com.kakao.pjh.exception.PasswordNotMatchException;
import com.kakao.pjh.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("LoginService")
@Transactional
public class LoginService implements APIService{
    @Autowired
    UserDaoImpl userDao;

    Encrypt encrypt;

    @Override
    public Response process(String apiKey, Request request) {
        User user = new User();
        UserDto userDto = (UserDto) request;
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());

        //암호화 APIKey 생성
        encrypt = new EncryptHelper();
        User selectedUser = userDao.selectUser(user.getId());

        if(!encrypt.isMatch(user.getPassword(), selectedUser.getPassword()))
            throw new PasswordNotMatchException(String.format("ID[%s] Incorrect authentication credentials", user.getId()));

        String token = TokenUtil.getRandomToken();
        if(token == null)
            throw new KakaoApiInternalServerError();

        //APIKey update
        selectedUser.setApikey(token);
        selectedUser.setLastLoginAt(new Date());

        LoginResponseToUser responseToUser = LoginResponseToUser.builder()
                .id(selectedUser.getId())
                .apiKey(selectedUser.getApikey())
                .name(selectedUser.getName())
                .createAt(selectedUser.getCreateAt())
                .lastLoginAt(selectedUser.getLastLoginAt())
                .build();
        responseToUser.setMessage(ResultComponent.Result.SUCC);
        return responseToUser;
    }
}
