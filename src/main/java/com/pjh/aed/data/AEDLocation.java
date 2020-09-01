package com.pjh.aed.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AEDLocation {
    @Id
    Long locationId;
    String address;
}
