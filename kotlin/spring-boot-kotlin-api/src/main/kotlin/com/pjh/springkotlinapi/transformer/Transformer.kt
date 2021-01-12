package com.pjh.springkotlinapi.transformer

interface Transformer <A, B>{
    fun transform(source: A): B
}