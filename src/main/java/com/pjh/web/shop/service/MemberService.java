package com.pjh.web.shop.service;

import com.pjh.web.shop.domain.Member;
import com.pjh.web.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

   /* @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<Member> foundMember = memberRepository.findByAccount(account);
        Member member = foundMember.orElseThrow(NotFoundMemberException::new);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return new User(member.getAccount(), member.getPassword(), authorityList);
    }*/

    //회원가입
    @Transactional
    public Long save(Member member) {
        member.setLastAccessDate(LocalDateTime.now());
        member.setRegistrationDate(LocalDateTime.now());

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member).getId();
    }

    //id check
    public boolean idCheck(String account){
        Optional<Member> byAccount = memberRepository.findByAccount(account);
        return byAccount.isPresent();
    }
}
