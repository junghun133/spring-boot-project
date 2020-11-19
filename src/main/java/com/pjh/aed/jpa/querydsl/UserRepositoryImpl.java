package com.pjh.aed.jpa.querydsl;

import com.pjh.aed.data.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.pjh.aed.data.entity.QUser.user;

public class UserRepositoryImpl implements UserRepositoryQD{
    private final JPAQueryFactory jpaQueryFactory;
    private EntityManager em;

    public UserRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
        this.em = em;
    }

    @Override
    public List<User> selectAllUserList(User userDto) {
        List<User> result = jpaQueryFactory
                .selectFrom(user)
                .fetch();

        return result;
    }

    @Override
    public List<User> selectUserId(String userId) {
        List<User> result = jpaQueryFactory
                .selectFrom(user)
                .where(userIdEq(userId))
                .fetch();
        return result;
    }

    @Override
    public List<User> selectUserIdAndPassword(String userId, String password) {
        List<User> result = jpaQueryFactory
                .selectFrom(user)
                .where(
                        userIdEq(userId),
                        usernameEq(password)
                )
                .fetch();
        return result;
    }

    private BooleanExpression userIdEq(String userId) {
        return StringUtils.isEmpty(userId) ? null : user.id.eq(userId);
    }

    private BooleanExpression usernameEq(String password) {
        return StringUtils.isEmpty(password) ? null : user.password.eq(password);
    }
}
