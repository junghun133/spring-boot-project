package com.pjh.springkotlinapi.transformer

import com.pjh.springkotlinapi.domain.User
import com.pjh.springkotlinapi.dto.UserResponse

fun User?.toUserResponse(): UserResponse{
    return UserResponse(
            id = this?.id ?: 1,
            account = this?.account ?: "test"
    )
}
