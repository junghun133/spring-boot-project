package com.pjh.aed.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AEDLocationRequestData extends CommonRequestData {
    Integer pageNo;
    Integer numOfRows;
    String state;
    String county;

    @Builder(builderMethodName = "AEDLocationRequestDataBuilder")
    public AEDLocationRequestData(String token, Integer pageNo, Integer numOfRows, String state, String county){
        setToken(token);
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.state = state;
        this.county = county;
    }
}
