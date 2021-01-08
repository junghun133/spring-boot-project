package com.pjh.springkotlinapi.dao

import com.pjh.springkotlinapi.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao : JpaRepository<User, Long>
