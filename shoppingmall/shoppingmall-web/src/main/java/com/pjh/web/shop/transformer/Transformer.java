package com.pjh.web.shop.transformer;

public interface Transformer<T1, T2> {
     T1 transform(T2 source);
}
