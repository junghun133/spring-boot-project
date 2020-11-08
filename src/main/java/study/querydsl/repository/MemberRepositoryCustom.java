package study.querydsl.repository;

import study.querydsl.entity.MemberSearchCondition;
import study.querydsl.entity.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
}
