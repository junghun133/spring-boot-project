package com.study.jpa.springdatajpa.repository.springdatajpa;

import com.study.jpa.springdatajpa.dto.MemberDto;
import com.study.jpa.springdatajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{
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

    @Query("select new com.study.jpa.springdatajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member> findListByUsername(String username); //collection
    Member findMemberByUsername(String username); //single
    Optional<Member> findOptionalByUsername(String username); //optional type

    // count query 를 하기와 같이 분리할 수 있음
//    @Query(value = "select m from Member m",
//            countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age +1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    //////////////////////////////fetch join 여러가지 방법 -> 조인해서 한번에 연관있는 many 쪽 data를 가져오기위해
    @Query("select m from Member  m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();


    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);
    ////////////////////////////////////////////////////////////

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);
}
