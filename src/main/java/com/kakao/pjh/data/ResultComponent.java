package com.kakao.pjh.data;

public interface ResultComponent {
    enum Result{
        SUCC(200, "성공"),
        FAIL(400, "실패"),
        INTERNAL_SERVER_ERROR(500, "서버 오류입니다"),
        NOT_FOUND(404, "요청받은 리소스를 찾을 수 없습니다"),
        NO_CONTENT(204, "응답할 데이터가 없습니다"),
        UNAUTHORIZED(401, "미 인증 오류입니다"),
        BAD_REQUEST(502,"잘못된 요청입니다");

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
