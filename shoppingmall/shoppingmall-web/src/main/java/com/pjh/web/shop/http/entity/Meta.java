package com.pjh.web.shop.http.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class Meta {
    int pageable_count;
    int total_count;
    Boolean is_end;
}
