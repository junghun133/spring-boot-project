package com.pjh.aed.service;

import com.pjh.aed.dao.UserDao;
import com.pjh.aed.service.executor.ServiceRequest;
import com.pjh.aed.service.executor.ServiceRunnerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AEDLocationSearchServiceRunner implements ServiceRunnerInterface {
    @Autowired
    UserDao userDao;

    @Override
    public String runService(ServiceRequest request) {

        return null;
    }
}
