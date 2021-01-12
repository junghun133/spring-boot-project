package com.pjh.web.shop.aspect;

public interface CommonLoggingValue {
    enum ControllerLog {
        ControllerLog, ControllerName, Method, Params, LogTime, RequestUrl, HttpMethod;
    }
    enum ServiceLog{
        ServiceLog, Arguments, Kind, Signature, Target;
    }
    enum TimeLog{
        TimeLog, StartTime, EndTime, ExecutionTime;
    }
}
