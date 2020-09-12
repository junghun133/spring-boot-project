package com.study.datajpa.repository.springdatajpa;

import com.study.datajpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository 생략 가능
public interface TeamRepository extends JpaRepository<Team, Long> {
}
