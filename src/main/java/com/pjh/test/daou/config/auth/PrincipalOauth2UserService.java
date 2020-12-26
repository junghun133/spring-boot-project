package com.pjh.test.daou.config.auth;

import com.pjh.test.daou.domain.Member;
import com.pjh.test.daou.domain.Role;
import com.pjh.test.daou.exception.NotFoundMemberException;
import com.pjh.test.daou.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        String provider = userRequest.getClientRegistration().getClientId(); // google
        String providerId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
        String username = provider + "_" + providerId;
        String password = bCryptPasswordEncoder.encode("google"); // 큰 의미없음
        Role role = Role.USER;

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
                    .role(role)
                    .build();

            memberRepository.save(member);
        }else {
            throw new NotFoundMemberException();
        }


        return new PrincipalDetails(member.toTo(), oAuth2User.getAttributes());
    }
}
