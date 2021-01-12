package com.study.jpa.hibernate;

import com.study.jpa.hibernate.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class hibernateJpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orm");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 비영속
//        Member member = new Member();
//        member.setId(100L);
//        member.setName("Name");

        // 영속
//        em.persist(member);
        // 준영속
//        em.detach(member);

        //db select query
        em.find(Member.class, 100L);
        // 1차 캐시에서 조회
        em.find(Member.class, 100L);

        em.close();
        emf.close();
    }
}
