package com.study.jpa.springdatajpa.repository; import com.study.jpa.springdatajpa.dto.MemberDto; import com.study.jpa.springdatajpa.entity.Member; import com.study.jpa.springdatajpa.entity.Team; import com.study.jpa.springdatajpa.repository.springdatajpa.MemberRepository; import com.study.jpa.springdatajpa.repository.springdatajpa.TeamRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.data.domain.Page; import org.springframework.data.domain.PageRequest; import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }



    @Test
    public void basicCRUD(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        //단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        /*변경 감지 (Dirty checking)
         * Entity 조회시 해당 엔티티의 조회상태 그대로 스냅샷을 만듬
         * transaction이 끝나는 시점 스냅샷과 비교하여 update query 전달 -> 영속성 컨텍스트가 관리하는 엔티티에만 적용
         * 전체 필드 업데이트하므로 특정 필드만 update시에는 Entity에 @DynamicUpdate
         */
        //findMember1.setUsername("PJH!!!!!");

        //리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        Long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        Long deleteCount = memberRepository.count();
        assertThat(deleteCount).isEqualTo(0);
    }


    @Test
    public void findByUsernameAndAgeGreaterThen(){
        Member member1 = new Member("AAAA", 10);
        Member member2 = new Member("AAAA", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findMemberBy(){
        List<Member> members = memberRepository.findMemberBy();
        List<Member> membersTop3 = memberRepository.findTop3MemberBy();
    }

    @Test
    public void findNamedQuery(){
        Member member1 = new Member("AAAA", 10);
        memberRepository.save(member1);
        Member member = memberRepository.findByUsername("AAAA").get(0);
        assertThat(member).isEqualTo(member1);
    }

    @Test
    public void testQuery(){
        Member member1 = new Member("AAAA", 10);
        Member member2 = new Member("BBBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findUser("AAAA", 10);
        assertThat(result.get(0)).isEqualTo(member1);
    }

    @Test
    public void findMemberDto(){
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        memberRepository.save(m1);
        m1.setTeam(team);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }

    }
    @Test
    public void findByNames(){
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        memberRepository.save(m1);
        m1.setTeam(team);

        List<Member> members = memberRepository.findByNames(Arrays.asList("AAAA", "BBB"));

        for (Member member : members) {
            System.out.println("member = " + member);
        }
    }

    @Test
    public void dynamicReturnTypeTest(){
        Member member1 = new Member("AAAA", 10);
        Member member2 = new Member("AAAA", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        Optional<Member> result = memberRepository.findOptionalByUsername("asd");
        assertThat(result.isPresent()).isEqualTo(false);
        System.out.println("result = " + result.orElse(null));
    }

    @Test
    public void paging(){
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        //sorting이 복잡해지면 jpql에서 직접 sort 구현하자
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        //when
        // 반환 type이 page 이기때문에 count query 도 select query 와 같이 실행함
        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        // page -> dto
        Page<MemberDto> memberDtos = page.map(m -> new MemberDto(m.getId(), m.getUsername(), null));

        //then
        List<Member> content = page.getContent();
        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(7);
        //page index는 0부터
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();

        /*List<Member> content1 = page.getContent();
        assertThat(page.getNumber()).isEqualTo(1);
        assertThat(content1.size()).isEqualTo(2);*/
    }

    @Test
    public void bulkUpdate(){
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("aaaa", 40));
        int bulkUpdate = memberRepository.bulkAgePlus(20);
        //상기 데이터는 실제 DB에 적용되지 않고 영속성G
        //em.clear(); --> repository 에서 @Modifying(clearAutomatically = true) option을 주면 안써도됌

        List<Member> result = memberRepository.findUser("aaaa",41);
        System.out.println("member5 = " + result.get(0));
    }

    //fetch test
    @Test
    public void findMemberLazy(){
        //given
        //memer1 -> teamA
        //memer2 -> teamB
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

//        List<Member> members = memberRepository.findAll();
        List<Member> members = memberRepository.findMemberFetchJoin();

        for (Member member : members) {
            System.out.println("member = " + member.getUsername()); //
            System.out.println("member.team = " + member.getTeam()); // fetch 처리가 되어 있지 않으면 team 여기까지는 가짜객체 (proxy 객체)
            System.out.println("member.team.name = " + member.getTeam().getName()); // 여기서부터 실제 Team 조회함 (N +1 문제 발생)

        }
    }

    @Test
    public void queryHint(){
        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        Member member = memberRepository.findReadOnlyByUsername("member1");
        member.setUsername("member2"); // hint로 read only가 적용되어 캐싱 오브젝트(영속성 컨텍스트)가 생성되지 않음, dirty checking 되지 않음
    }

    @Test
    @Disabled
    public void customRepositoryTest(){
        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

//        List<Member> memberCustom = memberRepository.findMemberCustom();
//        assertThat(memberCustom.isEmpty()).isFalse();
//        assertThat(memberCustom.get(0)).isEqualTo(member1);
    }
}
