package com.pjh.test.daou.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_MEMBER")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //일반생성 제한
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 1)
public class Member {
    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="ORDER_LINE_SEQ"
    )
    @Column(name = "member_id")
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false, unique = true)
    private String account;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(name = "last_access_date")
    private LocalDateTime lastAccessDate;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(Long id, String name, String account, String password, Role role) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.role = role;
    }
}
