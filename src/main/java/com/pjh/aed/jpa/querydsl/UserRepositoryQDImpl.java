package com.pjh.aed.jpa.querydsl;

import com.pjh.aed.data.dto.UserBindData;
import com.querydsl.jpa.JPQLQueryFactory;

import java.util.List;

import static com.pjh.aed.data.entity.QUser.user;

public class UserRepositoryQDImpl implements UserRepositoryQD{
    private final JPQLQueryFactory queryFactory;

    public UserRepositoryQDImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<UserBindData> userList(UserBindData userBindData) {
      /*  queryFactory
                .select(user)
                .from()*/
        return null;
    }
}
