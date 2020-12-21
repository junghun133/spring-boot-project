package com.pjh.test.daou.repository.dsl;

import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.QAttachmentImage;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.pjh.test.daou.domain.QAttachmentImage.attachmentImage;
import static com.pjh.test.daou.domain.QProductMaster.productMaster;


public class ProductMasterRepositoryImpl implements ProductMasterRepositoryQD{
    private final JPAQueryFactory queryFactory;
    private EntityManager em;

    public ProductMasterRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }

    //상품 리스트 조회 + keyword
    @Override
    public List<ProductMaster> selectProductList(int offset, int limit, String keyword) {
        return queryFactory
                .select(productMaster)
                .from(productMaster)
                .innerJoin(productMaster.attachmentImage, attachmentImage)
                .where(
                        isProductNameContainsKeyword(keyword)
                )
                .orderBy(productMaster.id.desc())
                .offset(offset)
                .limit(limit)
                .fetchJoin()
                .fetch();
    }

    private BooleanExpression isProductNameContainsKeyword(String keyword) {
        return StringUtils.isEmpty(keyword) ? null : productMaster.name.contains(keyword);
    }

/*    private BooleanExpression isPageable(Pageable pageable){
        if(pageable == null) return null; //첫조회
    }*/
}
