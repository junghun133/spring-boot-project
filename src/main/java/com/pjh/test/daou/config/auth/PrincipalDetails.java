package com.pjh.test.daou.config.auth;

import com.pjh.test.daou.dto.MemberTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//Security contextHolder 에 session key값 저장
//Security session => authentication => userDetails
@Getter
@Setter
public class PrincipalDetails implements UserDetails, OAuth2User {
    private MemberTO member;
    private Map<String, Object> attributes;

    //일반 login
    public PrincipalDetails(MemberTO member) {
        this.member = member;
    }

    //OAuth login
    public PrincipalDetails(MemberTO member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    //Oauth
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    //Oauth
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority) () -> member.getRole().getKey());

        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
