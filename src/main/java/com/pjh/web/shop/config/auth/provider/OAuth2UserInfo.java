package com.pjh.web.shop.config.auth.provider;

public interface OAuth2UserInfo {
    String getProviderId(); //google, facebook...
    String getProvider();
    String getEmail();
    String getName();
}
