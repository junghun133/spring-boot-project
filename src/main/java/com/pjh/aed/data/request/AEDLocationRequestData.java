package com.pjh.aed.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AEDLocationRequestData extends CommonRequestData {
    @Id
    Long locationId;
    String address;
}
