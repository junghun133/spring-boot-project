package com.pjh.test.daou.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class RecipientInfo {
    private String recipientName;
    private String recipientPhone;

    public RecipientInfo() {
    }

    public RecipientInfo(String recipientName, String recipientPhone) {
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
    }
}
