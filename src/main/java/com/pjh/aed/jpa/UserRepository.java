package com.pjh.aed.jpa;

import com.pjh.aed.data.entity.User;
import com.pjh.aed.jpa.querydsl.UserRepositoryQD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
