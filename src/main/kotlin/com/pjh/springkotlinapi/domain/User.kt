package com.pjh.springkotlinapi.domain

data class User(
        val id: Long,
        val account: String,
        val password: String,
        var name: String,
        var lastName: String,
        val address: String,
        val age: String?,
)