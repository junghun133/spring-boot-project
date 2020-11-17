package com.pjh.aed.jpa.querydsl;

import com.pjh.aed.data.entity.QUser;
import com.pjh.aed.data.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.pjh.aed.data.entity.QUser.user;

public class UserRepositoryQDImpl implements UserRepositoryQD{
    private final JPAQueryFactory jpaQueryFactory;

    public UserRepositoryQDImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<User> userList(User userDto) {
        List<User> result = jpaQueryFactory
                .selectFrom(user)
                .fetch();

        return result;
    }
}
