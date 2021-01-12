package study.querydsl.entity;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class AnotherMember {
    private String username;
    private int age;

    public AnotherMember() {
    }

    @QueryProjection
    public AnotherMember(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
