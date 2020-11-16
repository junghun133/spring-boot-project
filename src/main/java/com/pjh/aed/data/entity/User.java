package com.pjh.aed.data.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pjh.aed.data.response.Response;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TBL_USER")
@ToString(of = {"id", "name"})
public class User extends Response {
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
