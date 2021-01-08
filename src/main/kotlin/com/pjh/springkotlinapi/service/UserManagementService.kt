package com.pjh.springkotlinapi.service

import com.pjh.springkotlinapi.dto.AddUserRequest
import com.pjh.springkotlinapi.dto.UpdateUserRequest
import com.pjh.springkotlinapi.dto.UserResponse

interface UserManagementService {
    fun findById(id:Long): UserResponse?
    fun findAll(): List<UserResponse>
    fun save(addUserRequest: AddUserRequest): UserResponse
    fun update(updateUserRequest: UpdateUserRequest) : UserResponse
    fun deleteById(id:Long): Unit
}