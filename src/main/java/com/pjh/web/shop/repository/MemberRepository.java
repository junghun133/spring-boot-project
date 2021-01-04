package com.pjh.web.shop.repository;

import com.pjh.web.shop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByAccount(String account);
    Optional<Member> findByAccountAndPassword(String account, String password);
}
