package com.study.datajpa.repository;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

//1. Repository 이름 맞춰주고 + Impl 맞춰줘야함
//2. main repository 에 implements 추가
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List findMemberCustom() {
        return entityManager.createQuery("select m from Member m")
                .getResultList();
    }
}
