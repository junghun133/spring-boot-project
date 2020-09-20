package com.kakao.pjh.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter @Getter
@Table(name = "TBL_USER", indexes = @Index(columnList = "apikey"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long code;
    String id;
    String name;
    @JsonIgnore
    String password;
    String apikey;
    Date createAt;
    Date lastLoginAt;
}
