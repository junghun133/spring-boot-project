package com.study.jpa.hibernate.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "HibernateMember")
@Getter
@Setter
@Table(name = "TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    private Long id;

    @Column(name = "name"
            , insertable = true
            , updatable = true
            , nullable = false
    ,columnDefinition = "blah")
    private String name;

    private Integer age;

    //EnumType Ordinal 은 지양
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createDate;
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
    private LocalDate createDate;
    private LocalDateTime lastModifiedDate;

    @Lob //large content
    private String description;

    @Transient //not use
    private int temp;
}
