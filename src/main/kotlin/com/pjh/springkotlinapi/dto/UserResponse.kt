package com.pjh.springkotlinapi.dto

data class UserResponse (
        val code: Int,
        val message: String,
        val id: Long,
        val account: String
)