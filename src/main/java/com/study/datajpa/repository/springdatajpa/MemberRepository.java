package com.study.datajpa.repository.springdatajpa;

import com.study.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // spring data jpa가 자동으로 injection
}
