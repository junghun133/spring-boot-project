package com.pjh.test.daou.repository.dsl;

import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.QProductMaster;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.pjh.test.daou.domain.QProductMaster.productMaster;


public class ProductMasterRepositoryImpl implements ProductMasterRepositoryQD{
    private final JPAQueryFactory queryFactory;
    private EntityManager em;

    public ProductMasterRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }

    //상품 리스트 조회 + pagination (no offset)
    @Override
    public List<ProductMaster> selectProductList(Long productId, int pageSize, String keyword) {
        return queryFactory
                .selectFrom(productMaster)
                .where(
                        ltProductId(productId),
                        isProductContainsKeyword(keyword)
                )
                .orderBy(productMaster.id.desc())
                .limit(pageSize)
                .fetch();
    }

    private BooleanExpression isProductContainsKeyword(String keyword){
        return StringUtils.isEmpty(keyword) ? null : isProductNameContainsKeyword(keyword).or(isProductExplainContainsKeyword(keyword));
    }

    private BooleanExpression isProductNameContainsKeyword(String keyword) {
        return productMaster.name.like(keyword);
    }

    private BooleanExpression isProductExplainContainsKeyword(String keyword) {
        return productMaster.explain.like(keyword);
    }

    private BooleanExpression ltProductId(Long productId){
        if(productId == null) return null; //첫조회

        return productMaster.id.lt(productId);
    }
}
