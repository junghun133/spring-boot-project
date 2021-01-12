package com.pjh.springkotlinapi.dto

data class AddUserRequest(
        val account: String,
        val password: String,
        val name: String,
        val lastName: String,
        val address: String,
        val age: String?,
)