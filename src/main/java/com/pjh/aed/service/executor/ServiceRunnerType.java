package com.pjh.aed.service.executor;

import com.pjh.aed.service.AEDFullDownServiceRunner;

public enum ServiceRunnerType {
    NONE,
    AED_FULLDOWN,
    AED_LOCATION_INQUIRE,
    ;

    public static Class<?> find(ServiceRunnerType runnerType) {
        switch (runnerType) {
            case AED_FULLDOWN:
                return AEDFullDownServiceRunner.class;

            default:
                return null;
        }
    }
}
