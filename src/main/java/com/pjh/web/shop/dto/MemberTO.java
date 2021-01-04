package com.pjh.web.shop.dto;

import com.pjh.web.shop.domain.Member;
import com.pjh.web.shop.domain.Role;
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

    private String email;

    private Role role;

    public Member toEntity() {
        return new Member(id, name, account, password, email, role);
    }

    public MemberTO(Long id, String name, String account, String password, String email, Role role) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public MemberTO() {
    }
}
