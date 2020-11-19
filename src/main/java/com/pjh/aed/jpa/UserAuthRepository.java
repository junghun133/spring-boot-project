package com.pjh.aed.jpa;

import com.pjh.aed.data.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuthentication, Long> {
}
