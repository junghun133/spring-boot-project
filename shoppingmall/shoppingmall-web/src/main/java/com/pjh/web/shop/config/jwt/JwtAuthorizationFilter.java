package com.pjh.web.shop.config.jwt;

import com.nimbusds.jwt.JWT;
import com.pjh.web.shop.config.auth.PrincipalDetails;
import com.pjh.web.shop.domain.Member;
import com.pjh.web.shop.exception.NotFoundMemberException;
import com.pjh.web.shop.repository.MemberRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    //인증이나 권한이 필요한 주소 요청시 해당 필터를 탐
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소 요청");

        String jwtHeader = request.getHeader("Authorization");

        //header 유무 확인
        if(StringUtils.isNotEmpty(jwtHeader) || !jwtHeader.startsWith("Bearer")){
            chain.doFilter(request, response);
            return;
        }

        //JWT 토큰 검증해서 정상 사용자인지 확인
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        JWTUtil jwtUtil = new JWTUtil();
        String account = jwtUtil.extractUsername(token);

        if(account != null){
            Optional<Member> byAccount = memberRepository.findByAccount(account);
            Member member = byAccount.orElseThrow(NotFoundMemberException::new);

            PrincipalDetails principalDetails = new PrincipalDetails(member.toTo());
            //인증이 이미 되었으니 강제로 authentication 객체를 만듬
            //JWT token 서명 인증을 통해서 만들어진 객체
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null);

            //강제로 시큐리티의 세션에 접근하여 authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        chain.doFilter(request, response);
    }
}
