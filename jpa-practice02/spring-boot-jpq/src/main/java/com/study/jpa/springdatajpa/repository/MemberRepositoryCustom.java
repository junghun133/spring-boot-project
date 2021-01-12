package com.study.jpa.springdatajpa.repository;

import com.study.jpa.springdatajpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
