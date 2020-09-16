package com.kakao.pjh.service;

public interface Encrypt {
    String encrypt(String password);
    boolean isMatch(String password, String hashed);
}
