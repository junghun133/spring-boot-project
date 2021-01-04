package com.pjh.test.daou.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// /login post 요청시 username, password 받기위해 필터등록
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    //login 요청을 하면 로그인시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도 중");

        try {
            log.info(String.valueOf(request.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //1. username 받아 로그인시도 (authenticationManager 통해)
        //2. PrincipalDetailsService - loadUserByUsername() 실행
        //3. PrincipalDetails 를 세션에 담음(권한 관리 위해)
        //4. JWT 토큰을 응답
        return super.attemptAuthentication(request, response);
    }
}
