package com.pjh.springkotlinapi.service

import com.pjh.springkotlinapi.dao.UserDao
import com.pjh.springkotlinapi.dto.AddUserRequest
import com.pjh.springkotlinapi.dto.UpdateUserRequest
import com.pjh.springkotlinapi.dto.UserResponse
import com.pjh.springkotlinapi.transformer.addUserRequestTransformer
import org.springframework.stereotype.Service

@Service
class UserManagementServiceImpl(private val userDao: UserDao,
                                private val addUserRequestTransformer: addUserRequestTransformer) : UserManagementService {
    override fun findById(id: Long): UserResponse? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun save(addUserRequest: AddUserRequest): UserResponse {
        TODO("Not yet implemented")
    }

    override fun update(updateUserRequest: UpdateUserRequest): UserResponse {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }
}