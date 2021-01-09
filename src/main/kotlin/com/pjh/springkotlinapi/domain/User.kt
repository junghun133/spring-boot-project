package com.pjh.springkotlinapi.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
data class User(
        @Id @SequenceGenerator(name = USER_SEQUENCE, sequenceName = USER_SEQUENCE, initialValue = 1, allocationSize = 1)
        val id: Long,

        val account: String,
        val password: String,
        var name: String,
        var lastName: String,
        val address: String,
        val age: String? = null){
    companion object{
            const val USER_SEQUENCE: String = "USER_SEQUENCE"
    }
}