package com.study.jpa.springdatajpa.repository.jpa;

import com.study.jpa.springdatajpa.entity.Member;
import com.study.jpa.springdatajpa.entity.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class TeamJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public Team save(Team team){
        em.persist(team);
        return team;
    }


    public void delete(Team team){
        em.remove(team);
    }


    public List<Team> findAll(){
        return em.createQuery("select t from Team t", Team.class).getResultList();
    }

    public Optional<Team> findById(Long id){
        Team team = em.find(Team.class, id);
        return Optional.ofNullable(team);
    }

    public Long count(){
        return em.createQuery("select count(t) from Team t", Long.class).getSingleResult();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
