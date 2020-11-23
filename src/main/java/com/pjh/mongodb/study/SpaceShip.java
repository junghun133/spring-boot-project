package com.pjh.mongodb.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpaceShip {

    @Id
    private String id;
    private String type;
    private Integer engines;
    private Captain captain;

}
