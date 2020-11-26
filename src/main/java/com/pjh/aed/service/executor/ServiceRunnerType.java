package com.pjh.aed.service.executor;

import com.pjh.aed.service.AEDFullDownServiceRunner;
import com.pjh.aed.service.AEDLocationSearchServiceRunner;

public enum ServiceRunnerType {
    NONE,
    AED_FULLDOWN,
    AED_LOCATION_INQUIRE,
    ;

    public static Class<?> find(ServiceRunnerType runnerType) {
        switch (runnerType) {
            case AED_FULLDOWN:
                return AEDFullDownServiceRunner.class;
            case AED_LOCATION_INQUIRE:
                return AEDLocationSearchServiceRunner.class;

            default:
                return null;
        }
    }
}
