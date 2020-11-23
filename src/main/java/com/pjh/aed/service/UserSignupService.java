package com.pjh.aed.service;

import com.pjh.aed.service.executor.ServiceRequest;
import com.pjh.aed.service.executor.ServiceRunnerInterface;
import org.springframework.stereotype.Service;

@Service
public class UserSignupService implements ServiceRunnerInterface {
    @Override
    public String runService(ServiceRequest request) {
        return null;
    }
}
