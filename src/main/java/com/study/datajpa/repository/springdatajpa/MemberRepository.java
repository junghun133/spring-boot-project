package com.study.datajpa.repository.springdatajpa;

import com.study.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // spring data jpa가 자동으로 injection

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findMemberBy();

    List<Member> findTop3MemberBy();

    /*
    * Query annotation 은 Memeber domain의 findByUsername이 name인 named query를 찾기때문에 생략 가능
    */
    //@Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);
}
