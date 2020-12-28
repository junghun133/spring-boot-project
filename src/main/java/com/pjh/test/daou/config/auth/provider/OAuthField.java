package com.pjh.test.daou.config.auth.provider;

public interface OAuthField {
    enum google{
        google, sub, email, name
    }

    enum facebook{
        facebook, id, email, name
    }

    enum naver{
        naver, id, email, name
    }

    enum naver_key{
        response
    }
}
