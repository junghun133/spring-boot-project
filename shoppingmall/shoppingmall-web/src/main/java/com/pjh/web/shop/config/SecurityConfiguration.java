package com.pjh.web.shop.config;

import com.pjh.web.shop.config.auth.PrincipalDetailsService;
import com.pjh.web.shop.config.auth.PrincipalOauth2UserService;
import com.pjh.web.shop.config.filter.APIFilter;
import com.pjh.web.shop.config.jwt.JwtAuthenticationFilter;
import com.pjh.web.shop.config.jwt.JwtAuthorizationFilter;
import com.pjh.web.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인으로 등록됨
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//preAuthorize annotation 확성화, secured annotation 활성
//@PreAuthorize("hasRole("ROLE_ADMIN"), @Secured("ROLE_ADMIN")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    MemberRepository memberRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception { //filter chain proxy 생성
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/landing/**", "/media/**", "/fonts/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //http request에 대한 보안설정
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").authenticated()

                .antMatchers("/api/v1/manager/**").authenticated()
                .antMatchers("/**").permitAll();

        http.addFilterBefore(new APIFilter(), BasicAuthenticationFilter.class); //api filter config class 구현
        http.addFilter(corsFilter);
        http.addFilter(new JwtAuthenticationFilter(authenticationManager())); // form login을 사용하지않을때 필터로 구현하면 좋을 것 같음
        http.addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository));

//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.httpBasic().disable();
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")// login url 호출시 시큐리티가 낚아채서 대신 로그인 진행
                .defaultSuccessUrl("/home")
                .usernameParameter("account")
                .permitAll();

        http.oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
        //구글로그인 완료된 뒤에 후처리 필요

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);

        http.exceptionHandling()
                .accessDeniedPage("/denied");

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}