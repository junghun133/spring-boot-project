package com.pjh.web.shop.api.service;

import com.pjh.web.shop.config.auth.PrincipalDetails;
import com.pjh.web.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberAPIService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public void apilogin(String account, String password) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(account, password);
        //PrincipalDetailService의 loadUserByUsername() 실행
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        System.out.println(principalDetails.getMember().getId()); //login 정상되었을때

        //authentication 객체 return -> session 영역 저장
        //JWT 토큰을 사용하면서 세션을 만들 이유가 없음.
        // 단지 권한처리때문에 session에 넣어준다
    }
}
