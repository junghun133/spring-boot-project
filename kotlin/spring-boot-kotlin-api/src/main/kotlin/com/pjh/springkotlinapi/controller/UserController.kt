package com.pjh.springkotlinapi.controller

import com.pjh.springkotlinapi.dto.AddUserRequest
import com.pjh.springkotlinapi.dto.UpdateUserRequest
import com.pjh.springkotlinapi.dto.UserResponse
import org.springframework.http.ResponseEntity

interface UserController {
    fun findById(id:Long): ResponseEntity<UserResponse>?
    fun findAll(): ResponseEntity<List<UserResponse>>
    fun save(addUserRequest: AddUserRequest): ResponseEntity<UserResponse>
    fun update(updateUserRequest: UpdateUserRequest) : ResponseEntity<UserResponse>
    fun deleteById(id:Long): ResponseEntity<Unit>
}