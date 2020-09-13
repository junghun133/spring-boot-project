package com.study.datajpa.repository.springdatajpa;

import com.study.datajpa.dto.MemberDto;
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

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select new com.study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();
}
