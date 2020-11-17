package com.pjh.aed.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TBL_USER")
@ToString(of = {"id", "name"})
public class User {
    @Id
    @GeneratedValue
    @NotNull
    Long userCode;

    @NotNull
    String name;

    @NotNull
    String id;

    @OneToMany(mappedBy = "user") //UserAuthentication 이 연관관계 주인
    List<UserAuthentication> userAuthenticationList = new ArrayList<>();
}
