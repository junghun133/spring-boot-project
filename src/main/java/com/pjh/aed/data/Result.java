package com.pjh.aed.data;

public interface Result {
    enum Code{
        SUCC("성공"), FAIL("실패");

        String value;
        Code(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    enum DetailMessage{
        Fail_Default("요청에 실패 하였습니다. 관리자에게 문의바랍니다"),
        Fail_NotFoundUser("조회된 유저가 없습니다."),
        Success("");

        String cause;
        DetailMessage(String cause){
            this.cause = cause;
        }
        public String getCause(){
            return cause;
        }
    }
}
