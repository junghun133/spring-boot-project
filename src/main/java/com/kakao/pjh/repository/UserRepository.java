package com.kakao.pjh.repository;

import com.kakao.pjh.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.id = :id")
    Optional<User> findByUserId(@Param("id") String id);

    @Query("select count(u) from User u where u.apikey = :apiKey")
    Long countByApiKey(@Param("apiKey") String apiKey);
}
