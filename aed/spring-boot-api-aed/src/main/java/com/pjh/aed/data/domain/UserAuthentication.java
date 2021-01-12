package com.pjh.aed.data.domain;

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
    private Long authId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String token;
    private String use;
}
