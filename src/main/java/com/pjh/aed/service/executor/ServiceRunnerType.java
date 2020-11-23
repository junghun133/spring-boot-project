package com.pjh.aed.service.executor;

import com.pjh.aed.service.UserCreateTokenService;
import com.pjh.aed.service.UserFindAccountService;
import com.pjh.aed.service.UserSignupService;

public enum ServiceRunnerType {
    NONE,
    USER_SIGNUP,
    USER_FIND,
    USER_CREATE_TOKEN;

    public static Class<?> find(ServiceRunnerType runnerType) {
        switch (runnerType) {
            case USER_SIGNUP:
                return UserSignupService.class;

            case USER_FIND:
                return UserFindAccountService.class;

            case USER_CREATE_TOKEN:
                return UserCreateTokenService.class;

            default:
                return null;
        }
    }
}
