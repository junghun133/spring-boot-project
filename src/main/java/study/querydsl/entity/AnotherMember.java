package study.querydsl.entity;

import lombok.Data;

@Data
public class AnotherMember {
    private String username;
    private int age;

    public AnotherMember() {
    }

    public AnotherMember(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
