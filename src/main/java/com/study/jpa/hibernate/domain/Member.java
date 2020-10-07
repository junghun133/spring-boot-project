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
//allocationSize 증가값 -> 50으로 설정하여 네트워크 부하를 줄일 수 있음(미리 가져온 sequence 값으로 memory에서만 호출함) 안쓰면 낭비 주의
public class Member {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // db 방언에 맞춰 실행
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
        기본키 생성을 dbms에 위임 ex. mysql = auto_increment
        Identity 전략을 사용하는 entity에 persistent를 하게되면 key를 얻어와야하기때문에 트랜잭션 중간에 insert query가 query 저장소에서 시작된다다
    */
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) // 기본 sequence 생성하여 call next value,
    // 영속성 컨텍스트에는 항상 key가 있어야하기때문에 시퀀스 전략일때 persist 호출시 sequence call next를 먼저 한다.
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
