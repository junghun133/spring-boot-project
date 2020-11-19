package com.pjh.aed.jpa;

import com.pjh.aed.data.entity.User;
import com.pjh.aed.jpa.querydsl.UserRepositoryQD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQD {
    Optional<User> findById(String id);
    Optional<User> findByIdAndPassword(String id);
}
