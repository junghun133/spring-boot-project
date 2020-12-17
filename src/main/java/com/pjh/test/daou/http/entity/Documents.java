package com.pjh.test.daou.http.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Documents {
    Long id;
    String name;
    String explain;
    int price;
    String imagePath;
}
