package com.pjh.test.daou.config.auth;

import com.pjh.test.daou.domain.Member;
import com.pjh.test.daou.exception.NotFoundMemberException;
import com.pjh.test.daou.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// /login 요청시 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 함수 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<Member> foundMember = memberRepository.findByAccount(account);
        Member member = foundMember.orElseThrow(NotFoundMemberException::new);

        return new PrincipalDetails(member.toTo());
    }

}
