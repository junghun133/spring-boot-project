package com.study.jpa.shop.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaShopMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaShop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.close();
        emf.close();
    }
}

/*
* 연관관계의 주인

-> 외래키가 잇는 곳을 주인(owner)로
 team(one) : member(many) 관계에서 member를 연관관계 주인으로 (FK가 있는 곳)
mappedby 는 읽기전용(JPA의 관리 대상이 아님 )

* 양방향 매핑시 가장 많이 하는 실수
-> 연관관계의 주인에 값을 입력하지않음

* 양방향 연관관계시 연관관계 편의 메소드를 사용하자
void changeTeam(Team team){
	this.team = team;
	team.getMemebers().add(this);
}

* toString, lombok 무한루프를 조심하자(API Controller에서 response 줄때)

 */