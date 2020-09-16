package com.kakao.pjh.service;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptHelper implements Encrypt{

    @Override
    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean isMatch(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
