package com.study.datajpa.repository.springdatajpa;

import com.study.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // spring data jpa가 자동으로 injection

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findMemberBy();

    List<Member> findTop3MemberBy();
}
