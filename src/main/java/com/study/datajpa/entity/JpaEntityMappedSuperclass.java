package com.study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

//일반적으로, 상속 관계 매핑 전략에서 부모 클래스와 자식 클래스 모두 데이타베이스 테이블과 매핑을 한다.
//이와 달리, 부모 클래스를 상속받는 자식클래스에게 매핑 정보만 제공하고 싶을때 이 어노테이션을 사용하면 된다
@MappedSuperclass
@Getter @Setter
public class JpaEntityMappedSuperclass {
    @Column(updatable = false)
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createDate = now;
        updateDate = now;
    }

    @PreUpdate
    public void preUpdate(){
        updateDate = LocalDateTime.now();
    }

    @LastModifiedDate
    private LocalDateTime  lastModifiedDate;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;
}
