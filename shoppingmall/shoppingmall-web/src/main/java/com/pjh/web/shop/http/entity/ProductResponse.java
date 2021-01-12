package com.pjh.web.shop.http.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductResponse {
    Meta meta;
    List<Documents> documents;
}
