package com.pjh.web.shop.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjh.web.shop.config.auth.PrincipalDetails;
import com.pjh.web.shop.dto.MemberTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// /login post 요청시 username, password 받기위해 필터등록
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final JWTUtil jwtUtil;

    //login 요청을 하면 로그인시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        MemberTO memberTO = null;
        try {
            memberTO = objectMapper.readValue(request.getInputStream(), MemberTO.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(memberTO.getAccount(), memberTO.getPassword());

            //PrincipalDetailService의 loadUserByUsername() 실행
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //1. username 받아 로그인시도 (authenticationManager 통해)
        //2. PrincipalDetailsService - loadUserByUsername() 실행
        //3. PrincipalDetails 를 세션에 담음(권한 관리 위해)
        //4. JWT 토큰을 응답
        return super.attemptAuthentication(request, response);
    }

    //attemptAuthenticaqtion 실행 후 인증 정상일시 함수 실행
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //method 진입시 인증완료된것
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        String jwtToken = jwtUtil.generateToken(principalDetails, 1000 * 60 * 60 * 10);

        response.addHeader("Authorization", "Bearer " + jwtToken);
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
