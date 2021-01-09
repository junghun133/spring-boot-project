package com.pjh.springkotlinapi.service

import com.pjh.springkotlinapi.dao.UserDao
import com.pjh.springkotlinapi.domain.User
import com.pjh.springkotlinapi.dto.AddUserRequest
import com.pjh.springkotlinapi.dto.UpdateUserRequest
import com.pjh.springkotlinapi.dto.UserResponse
import com.pjh.springkotlinapi.transformer.addUserRequestTransformer
import com.pjh.springkotlinapi.transformer.toUserResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserManagementServiceImpl(private val userDao: UserDao,
                                private val addUserRequestTransformer: addUserRequestTransformer) : UserManagementService {
    override fun findById(id: Long): UserResponse? = this.findUserById(id).toUserResponse()

    override fun findAll(): List<UserResponse> = this.userDao.findAll().map ( User::toUserResponse )

    override fun save(addUserRequest: AddUserRequest): UserResponse {
        return this.saveOrUpdate(
                addUserRequestTransformer.transform(addUserRequest)
        )
    }

    override fun update(updateUserRequest: UpdateUserRequest): UserResponse {
        val user = this.findUserById(updateUserRequest.id) ?: throw IllegalStateException("${updateUserRequest.id} not found")

        return this.saveOrUpdate(user.apply {
            this.name = updateUserRequest.name
            this.lastName = updateUserRequest.lastName
        })
    }

    override fun deleteById(id: Long) : Unit = this.userDao.deleteById(id)

    private fun findUserById(id: Long): User? = this.userDao.findByIdOrNull(id)

    private fun saveOrUpdate(user: User) : UserResponse = this.userDao.save(user).toUserResponse()
}