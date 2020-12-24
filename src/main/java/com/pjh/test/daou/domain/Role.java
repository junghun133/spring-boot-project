package com.pjh.test.daou.domain;

import lombok.Getter;

@Getter
public enum Role {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    Role(String key) {
        this.key = key;
    }

    private String key;
}
