package com.pjh.aed.data.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "TBL_AUTH")
public class UserAuthentication {
    @Id @GeneratedValue
    @Column(name = "auth_id")
    private Long authKey;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String authData;
}
