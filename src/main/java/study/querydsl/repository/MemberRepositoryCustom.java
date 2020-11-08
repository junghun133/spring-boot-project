package study.querydsl.repository;

import study.querydsl.entity.MemberSearchCondition;
import study.querydsl.entity.MemberTeamDto;

import java.util.List;

/**
 * tip! custom이 아니라 특화된 querydsl 구현체면 별도로 그냥 @Repository 등록해놓고 injection해서 쓰자
 */
public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
}



