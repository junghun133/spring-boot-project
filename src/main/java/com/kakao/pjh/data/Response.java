package com.kakao.pjh.data;

public interface Response {
    public enum Result{
        SUCC(200), FAIL(400);

        int code;

        Result(int code) {
            this.code = code;
        }

        public int getCode(){
            return code;
        }
    }
}
