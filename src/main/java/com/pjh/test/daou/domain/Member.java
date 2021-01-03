package com.pjh.test.daou.domain;


import com.pjh.test.daou.dto.MemberTO;
import lombok.*;

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
        initialValue = 2, allocationSize = 1)
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

    @Column(length = 255, nullable = true)
    private String email;

    @Column(name = "last_access_date")
    private LocalDateTime lastAccessDate;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    private String provider;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String apiToken;

    @Builder
    public Member(Long id, String name, String account, String password, String email, LocalDateTime lastAccessDate, LocalDateTime registrationDate, String provider, String providerId, Role role, String apiToken) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.email = email;
        this.lastAccessDate = lastAccessDate;
        this.registrationDate = registrationDate;
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
        this.apiToken = apiToken;
    }

    public Member(Long id, String name, String account, String password, String email, Role role) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public MemberTO toTo(){
         return new MemberTO(id, name, account, password, email, role);
    }
}
