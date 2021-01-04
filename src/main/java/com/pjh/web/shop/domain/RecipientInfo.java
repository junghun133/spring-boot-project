package com.pjh.web.shop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class RecipientInfo {
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;

    public RecipientInfo() {
    }

    public RecipientInfo(String recipientName, String recipientPhone, String recipientAddress) {
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.recipientAddress = recipientAddress;
    }
}
