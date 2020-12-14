package com.pjh.test.daou.controller;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TradeForm {
    private String orderName;
    private String orderPhone;
    private String recipientName;
    private String recipientPhone;
    private String recipientJibunAddress;
    private String recipientDetailAddress;
}
