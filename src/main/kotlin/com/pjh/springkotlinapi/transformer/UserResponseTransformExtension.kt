package com.pjh.springkotlinapi.transformer

import com.pjh.springkotlinapi.domain.User
import com.pjh.springkotlinapi.dto.UserResponse

fun User?.toUserResponse(resultCode : ResultCode): UserResponse{
    return UserResponse(
            code = resultCode.code,
            message = resultCode.message,
            id = this?.id ?: 1,
            account = this?.account ?: "test"
    )
}
