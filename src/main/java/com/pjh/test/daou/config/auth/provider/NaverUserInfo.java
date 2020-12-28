package com.pjh.test.daou.config.auth.provider;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; //oauth2User.getAttributes();

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get(OAuthField.facebook.id.name());
    }

    @Override
    public String getProvider() {
        return "facebook";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get(OAuthField.facebook.email.name());
    }

    @Override
    public String getName() {
        return (String) attributes.get(OAuthField.facebook.name.name());
    }
}
