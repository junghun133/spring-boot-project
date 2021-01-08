package com.pjh.springkotlinapi.dto

data class UpdateUserRequest (
        val id: Long,
        val account: String,
        val password: String,
        val name: String,
        val lastName: String,
        val address: String,
        val age: String?,
)