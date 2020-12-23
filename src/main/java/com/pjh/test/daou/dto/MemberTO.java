package com.pjh.test.daou.dto;

import com.pjh.test.daou.domain.Member;
import com.pjh.test.daou.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MemberTO {

    private Long id;

    private String name;

    private String account;

    private String password;

    private LocalDateTime lastAccessDate;

    private LocalDateTime registrationDate;

    private Role role;

    public Member toEntity() {
        return new Member(id, name, account, password, role);
    }
}
