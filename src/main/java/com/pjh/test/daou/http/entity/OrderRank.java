package com.pjh.test.daou.http.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRank {
    private String percent;
    private String productName;
    private int orderCount;

    public void getPercent(int totalCount){
        double per = (double) orderCount / (double) totalCount * 100.0;
        if(per < 0 || per > 100){
            throw new NumberFormatException();
        }

        percent = (int)per + "%";
    }
}
