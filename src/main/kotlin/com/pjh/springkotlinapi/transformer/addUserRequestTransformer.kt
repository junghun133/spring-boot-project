package com.pjh.springkotlinapi.transformer

import com.pjh.springkotlinapi.domain.User
import com.pjh.springkotlinapi.dto.AddUserRequest
import org.springframework.stereotype.Component

@Component
class addUserRequestTransformer : Transformer<AddUserRequest, User>{

    override fun transform(source: AddUserRequest): User {
        return User(
                id = 1L,
                account = source.account,
                password = source.password,
                name = source.name,
                lastName = source.lastName,
                address = source.address,
                age = source.age
        )
    }

}