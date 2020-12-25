package com.pjh.test.daou.config;

import com.pjh.test.daou.config.auth.PrincipalDetailsService;
import com.pjh.test.daou.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인으로 등록됨
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//preAuthorize annotation 확성화, secured annotation 활성
//@PreAuthorize("hasRole("ROLE_ADMIN"), @Secured("ROLE_ADMIN")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
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
                .antMatchers("/**").permitAll();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")// login url 호출시 시큐리티가 낚아채서 대신 로그인 진행
                .defaultSuccessUrl("/home")
                .usernameParameter("account")
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);

        http.exceptionHandling()
                .accessDeniedPage("/denied");
    }
}
