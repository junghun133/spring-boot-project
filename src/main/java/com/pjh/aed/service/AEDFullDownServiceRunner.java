package com.pjh.aed.service;

import com.pjh.aed.dao.AuthDao;
import com.pjh.aed.dao.UserDao;
import com.pjh.aed.service.executor.ServiceRequest;
import com.pjh.aed.service.executor.ServiceRunnerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AEDFullDownServiceRunner implements ServiceRunnerInterface {
    UserDao userDao;
    AuthDao authDao;

    @Override
    public String runService(ServiceRequest request) {
//        String token = request.getRequest().getToken();
//        authDao.isToken(token);

        return null;
    }
}
