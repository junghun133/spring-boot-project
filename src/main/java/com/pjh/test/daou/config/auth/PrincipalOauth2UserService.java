package com.pjh.test.daou.config.auth;

import com.pjh.test.daou.config.auth.provider.*;
import com.pjh.test.daou.domain.Member;
import com.pjh.test.daou.domain.Role;
import com.pjh.test.daou.exception.InternalServerException;
import com.pjh.test.daou.exception.NotFoundMemberException;
import com.pjh.test.daou.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    MemberRepository memberRepository;

    /**
     * google login process
     * 구글로그인 버튼 클릭
     * -> 구글로그인창
     * -> 로그인 완료
     * -> code 리턴(OAuth-Client 라이브러리)
     * -> AccessToken 요청
     * -> userRequest 정보 수신
     * -> loadUser 함수 호출
     * -> 구글로부터 프로필 수신
     */

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        System.out.println("userRequest = " + userRequest.getClientRegistration());
//        System.out.println("userAccessToken = " + userRequest.getAccessToken().getTokenValue());
//        System.out.println("userAttributes = " + super.loadUser(userRequest).getAttributes());
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if(registrationId.contentEquals(OAuthField.google.google.name())){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }else if(registrationId.contentEquals(OAuthField.facebook.facebook.name())){
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }else if(registrationId.contentEquals(OAuthField.naver.naver.name())){
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get(OAuthField.naver_key.response.name()));
        }else {
            throw new InternalServerException("RegistrationId error, OAuth reg id:" + registrationId );
        }

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String username = provider + "_" + providerId;
        String password = bCryptPasswordEncoder.encode("google"); // 큰 의미없음
        Optional<Member> account = memberRepository.findByAccount(username);

        Member member = null;
        if(!account.isPresent()){
             member = Member.builder()
                    .provider(provider)
                    .providerId(providerId)
                    .account(username)
                    .email(email)
                    .name(username)
                    .password(password)
                    .role(Role.USER)
                    .build();

            memberRepository.save(member);
        }else {
            log.info("already signed up");
            throw new NotFoundMemberException();
        }
        return new PrincipalDetails(member.toTo(), oAuth2User.getAttributes());
    }
}
