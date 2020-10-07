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
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 1)
//initialValue 시작
//allocationSize 증가값
public class Member {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // db 방언에 맞춰 실행
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
        기본키 생성을 dbms에 위임 ex. mysql = auto_increment
        Identity 전략을 사용하는 entity에 persistent를 하게되면 key를 얻어와야하기때문에 트랜잭션 중간에 insert query가 query 저장소에서 시작된다다
    */
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) // 기본 sequence 생성하여 call next value
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
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
