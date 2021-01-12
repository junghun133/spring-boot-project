package com.pjh.springkotlinapi.transformer

enum class ResultCode(val code: Int, val message: String) {
    sucess(200, "SUCC"), fail(500, "FAIL")
}
