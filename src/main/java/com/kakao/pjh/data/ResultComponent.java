package com.kakao.pjh.data;

public interface ResultComponent {
    enum Result{
        SUCC(200, "성공"), FAIL(400, "실패");

        int code;
        String message;

        Result(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode(){
            return code;
        }

        public String getMessage(){
            return message;
        }
    }
}
