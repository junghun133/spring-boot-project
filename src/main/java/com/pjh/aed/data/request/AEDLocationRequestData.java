package com.pjh.aed.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AEDLocationRequestData extends CommonRequestData {
    Integer pageNo;
    Integer numOfRows;
    String longitude;
    String latitude;

    @Builder(builderMethodName = "AEDLocationRequestDataBuilder")
    public AEDLocationRequestData(String token, Integer pageNo, Integer numOfRows, String longitude, String latitude){
        setToken(token);
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.longitude = longitude;
        this.latitude = latitude;;
    }
}
