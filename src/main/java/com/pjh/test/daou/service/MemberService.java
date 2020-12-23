package com.pjh.test.daou.service;

import com.pjh.test.daou.domain.Member;
import com.pjh.test.daou.dto.MemberTO;
import com.pjh.test.daou.exception.NotFoundMemberException;
import com.pjh.test.daou.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<Member> foundMember = memberRepository.findByAccount(account);
        Member member = foundMember.orElseThrow(NotFoundMemberException::new);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return new User(member.getAccount(), member.getPassword(), authorityList);
    }

    @Transactional
    public Long save(MemberTO memberTO) {
        Member member = memberTO.toEntity();
        member.setLastAccessDate(LocalDateTime.now());
        member.setRegistrationDate(LocalDateTime.now());

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member).getId();
    }
}
