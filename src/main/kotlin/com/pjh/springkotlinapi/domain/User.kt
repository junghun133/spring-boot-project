package com.pjh.springkotlinapi.domain

data class User(
        val id: Long,
        val account: String,
        val password: String,
        val name: String,
        val lastName: String,
        val address: String,
        val age: String?,
)