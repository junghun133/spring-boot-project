package com.pjh.test.daou.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter @Getter
public class TradeForm {
    private Long productId;

    @NotEmpty(message = "주문자 이름은 필수입니다.")
    private String orderName;
    private String orderPhone;
    private String recipientName;
    private String recipientPhone;

    @NotEmpty(message = "주소 정보는 필수입니다.")
    private String recipientJibunAddress;

    @NotEmpty(message = "주소 정보는 필수입니다.")
    private String recipientDetailAddress;

    private int orderCount;

    public String getRecipientAddress(){
        return "지번:" +
                recipientJibunAddress +
                " 상세주소:" +
                recipientDetailAddress;
    }
}
