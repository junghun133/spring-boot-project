package com.pjh.test.daou.config.auth.provider;

public interface OAuth2UserInfo {
    String getProviderId(); //google, facebook...
    String getProvider();
    String getEmail();
    String getName();
}
