package com.pjh.aed.jpa;

import com.pjh.aed.data.domain.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuthentication, Long> {
    Optional<UserAuthentication> findByToken(String token);
}
