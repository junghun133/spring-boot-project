package study.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

/**
 * queryDSL 표현식
 * // comparison
 * expr lt 5  				-> expr < 5
 * expr loe 5 				-> expr <= 5
 * expr gt 5 				-> expr > 5
 * expr goe 5 				-> expr >= 5
 * expr notBetween(2,6) 	-> expr not between (2,6)
 *
 * // numeric
 * expr add 3 		-> expr + 3
 * expr subtract 3 -> expr - 3
 * expr divide 3 	-> expr / 3
 * expr multiply 3 -> expr * 3
 * expr mod 5 		-> expr % 5
 */
@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void before(){
        jpaQueryFactory = new JPAQueryFactory(em); // field로 선언해도된다 동시성이슈(x)

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();
    }

    @Test
    public void startJPQL(){
        //find member1
        Member findMember = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl(){
//        QMember member = new QMember("m"); static 변수로 사용
        Member findMember = jpaQueryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1")) //파라미터 바인딩 처리
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search(){
        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.between(10, 30)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchAndParam(){
        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"), //and 인경우 이렇게 표현가능
                        (member.age.between(10, 30)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void queryResultSearch(){
        //페이징에서 사용
        //성능 중요한 페이징쿼리에서는 사용하지않는다
        QueryResults<Member> result = jpaQueryFactory
                .selectFrom(member)
                .fetchResults();

        assertThat(result.getTotal()).isEqualTo(4);
    }

    /**
     *  회원 정렬 순서
     *  1. 회원 나이 내림차순(desc)
     *  2. 회원 이름 올림차순(asc)
     *  단 2에서 회원 이름 없으면 마지막에 출력(nulls last)
     */
    @Test
    public void sort(){
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();
       Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }


    /**
     *  팀의 이름과 각 팀의 평균 연령을 구해라
     */
    @Test
    public void group(){
        List<Tuple> result = jpaQueryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamB.get(team.name)).isEqualTo("teamB");
    }



    /**
     * 예) 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인
     * JPQL: select m, f from Member m left join m.team t on t.name = 'teamA'
     */

    @Test
    public void join_on_filtering(){
        List<Tuple> result = jpaQueryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team).on(team.name.eq("teamA"))
                //leftjoin -> join 으로 변경시 where(team.name.eq("teamA")) 와 결과가 동일하다
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }
    /**
     *  연관관계 없는 엔티티 외부 조인
     *  회원의 이름이 팀 이름과 같은 대상 외부 조인
     *
     *  하이버네이트 5.1부터 on 을 사용해서 서로 관계가 없는 필드로 외부 조인하는 기능이 추가되었다. 물론 내
     * 부 조인도 가능하다.
     * 주의! 문법을 잘 봐야 한다. leftJoin() 부분에 일반 조인과 다르게 엔티티 하나만 들어간다.
     * 일반조인: leftJoin(member.team, team)
     * on조인: from(member).leftJoin(team).on(xxx)
     */
    @Test
    public void join_on_no_relation(){
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        List<Tuple> result = jpaQueryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team).on(member.username.eq(team.name))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    /**
     *  fetch join
     */
    @PersistenceUnit
    EntityManagerFactory emf;
    @Test
    public void fetchJoinNo() throws Exception {
        em.flush();
        em.clear();
        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        boolean loaded =
                emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("페치 조인 미적용").isFalse();
    }

    /**
     * 즉시 로딩
     */
    @Test
    public void fetchJoinUse() throws Exception {
        em.flush();
        em.clear();
        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();
        boolean loaded =
                emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("페치 조인 적용").isTrue();
    }


    /**
     * 서브쿼리 사용법
     * 나이가 가장 많은 회원 조회
     *
     * from 절에 서브쿼리는 지원하지 않는다.
     * 1. 서브쿼리를 join으로 변경한다
     * 2. 애플리케이션에서 2번 호출한다
     * 3. native query를 사용한다
     */
    @Test
    public void subQuery() throws Exception {
        //Qtype이 중복되면 안되니 sub용 Qtype 생성
        QMember memberSub = new QMember("memberSub");

        List<Member> result = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.eq(
//                        .where(member.age.in(
                        JPAExpressions
                                .select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetch();
        assertThat(result).extracting("age")
                .containsExactly(40);
    }

    /**
     * 간단한 case 문
     */
    @Test
    public void basicCase(){
        List<String> result = jpaQueryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
    }

    /**
     * 복잡한 case문
     *  이왕이면 애플리케이션 로직에서하자
     */
    @Test
    public void complexCase(){
        List<String> result = jpaQueryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0~20살")
                        .when(member.age.between(21, 30)).then("21~30살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
    }

    /**
     * 예를 들어서 다음과 같은 임의의 순서로 회원을 출력하고 싶다면?
     * 1. 0 ~ 30살이 아닌 회원을 가장 먼저 출력
     * 2. 0 ~ 20살 회원 출력
     * 3. 21 ~ 30살 회원 출력
     */
    @Test
    public void exampleComplexCase(){
        NumberExpression<Integer> rankPath = new CaseBuilder()
                .when(member.age.between(0, 20)).then(2)
                .when(member.age.between(21, 30)).then(1)
                .otherwise(3);
        List<Tuple> result = jpaQueryFactory
                .select(member.username, member.age, rankPath)
                .from(member)
                .orderBy(rankPath.desc())
                .fetch();
        for (Tuple tuple : result) {
            String username = tuple.get(member.username);
            Integer age = tuple.get(member.age);
            Integer rank = tuple.get(rankPath);
            System.out.println("username = " + username + " age = " + age + " rank = "
                    + rank);
        }
    }

    /**
     * 상수 더하기
     */
    @Test
    public void expression(){
        Tuple result = jpaQueryFactory
                .select(member.username, Expressions.constant("A"))
                .from(member)
                .fetchFirst();
    }

    /**
     * 문자더하기
     *
     * output: member1_10
     * member.age.stringValue() 부분이 중요한데, 문자가 아닌 다른 타입들은 stringValue() 로 문자로 변환할 수 있다. 이 방법은 ENUM을 처리할 때도 자주 사용한다
     */
    @Test
    public void concat(){
        String result = jpaQueryFactory
                .select(member.username.concat("_").concat(member.age.stringValue())) //casting 됌
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        System.out.println("result = " + result);
    }


    /**
     * JPQL에서 dto로 받는 소스(생성자 방식만 지원, DTO의 package 이름을 다 적어줘야해서 지저분)
     *
     * List<MemberDto> result = em.createQuery(
     *  "select new study.querydsl.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
     *  .getResultList();
     */
    //setter를 활용한 방법
    @Test
    public void findDtoBySetter(){
        List<AnotherMember> result = jpaQueryFactory
                .select(Projections.bean(AnotherMember.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
    }
    //field에 injection하는 방법
    @Test
    public void findDtoByField(){
        List<AnotherMember> result = jpaQueryFactory
                .select(Projections.fields(AnotherMember.class,
                        member.username,
                        //member.username.as("name"), 별칭 맞출때
                        member.age))
                /** 혹은 서브 쿼리
                 * ExpressionUtils.as(
                 *  JPAExpressions
                 *  .select(memberSub.age.max())
                 *  .from(memberSub), "age")
                 *  )
                 */
                .from(member)
                .fetch();
    }
    //Constructor 활용한 방법
    @Test
    public void findDtoByConttrutor(){
        List<AnotherMember> result = jpaQueryFactory
                .select(Projections.constructor(AnotherMember.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
    }


    /**
     * QueryProjection
     constructor와 비슷하지만 생성자 오류를 컴파일단계에서 막을수 있는 이점이 있다
     */
    @Test
    public void findDtoByQueryProjection(){
        List<AnotherMember> result = jpaQueryFactory
                .select(new QAnotherMember(member.username, member.age))
                .from(member)
                .fetch();
    }

    /**
     * 동적 쿼리를 해결하는 두가지 방식
     * BooleanBuilder
     * Where 다중 파라미터 사용
     * @throws Exception
     */
    @Test
    public void 동적쿼리_BooleanBuilder() throws Exception {
        String usernameParam = "member1";
        Integer ageParam = 10;
        List<Member> result = searchMember1(usernameParam, ageParam);
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        BooleanBuilder builder = new BooleanBuilder();
        if (usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }
        if (ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }
        return jpaQueryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
    }

    //where 조건에 BooleanExpression으로 null이 반환되면 무시된다
    //isServiceable (usernameEq + ageEq) 처럼 조합이 가능한 장점이 있음

    @Test
    public void 동적쿼리_WhereParam() throws Exception {
        String usernameParam = "member1";
        Integer ageParam = 10;
        List<Member> result = searchMember2(usernameParam, ageParam);
        Assertions.assertThat(result.size()).isEqualTo(1);
    }
    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(usernameEq(usernameCond), ageEq(ageCond))
                .fetch();
    }
    private BooleanExpression usernameEq(String usernameCond) {
        return usernameCond != null ? member.username.eq(usernameCond) : null;
    }
    private BooleanExpression ageEq(Integer ageCond) {
        return ageCond != null ? member.age.eq(ageCond) : null;
    }


    //쿼리 한번으로 대량 데이터 수정
/*    long count = queryFactory
            .update(member)
            .set(member.username, "비회원")
            .where(member.age.lt(28))
            .execute();


 */
    //기존 숫자에 1 더하기
/**    long count = queryFactory
            .update(member)
            .set(member.age, member.age.add(1))
            .execute();
   곱하기: multiply(x)
    update member set age = age + 1

   //쿼리 한번으로 대량 데이터 삭제
    long count = queryFactory
            .delete(member)
            .where(member.age.gt(18))
            .execute();
}*/

    //SQL Function 호출 예제
    // 직접 function을 구현시에는 direct 의 function 관련 구현을 하고 사용하자
    @Test
    private void sqlFunction(){
        List<String> result = jpaQueryFactory
                .select(Expressions.stringTemplate("function('replace', {0}, {1},{2})", member.username, "member", "M"))
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }

    }
}
