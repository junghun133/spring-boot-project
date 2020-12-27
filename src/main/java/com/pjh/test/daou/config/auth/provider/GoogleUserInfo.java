package com.pjh.test.daou.config.auth.provider;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class GoogleUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; //oauth2User.getAttributes();

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get(OAuthField.google.sub.name());
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get(OAuthField.google.email.name());
    }

    @Override
    public String getName() {
        return (String) attributes.get(OAuthField.google.name.name());
    }
}
