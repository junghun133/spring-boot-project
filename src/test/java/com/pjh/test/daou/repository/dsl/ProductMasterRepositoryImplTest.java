package com.pjh.test.daou.repository.dsl;

import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.repository.ProductMasterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ProductMasterRepositoryImplTest {
    @Autowired
    ProductMasterRepository productMasterRepository;

    @Test
    public void selectToProductListNoOffsetPaginationTest(){
        //given
        String keyword = "점퍼";
        //when
        List<ProductMaster> productMasters = productMasterRepository.selectProductList(null, 9, null);
        List<ProductMaster> productMastersWithKeyword = productMasterRepository.selectProductList(null, 9, keyword);

        for (ProductMaster productMaster : productMastersWithKeyword) {
            System.out.println("productMaster = " + productMaster.getName());
            System.out.println("productMaster id= " + productMaster.getId());
        }

        //then
        assertEquals("9개씩 페이징하여 가져오는지 확인", productMasters.size(), 9);
        assertTrue("키워드로 검색하여 가져온 데이터 검증", productMastersWithKeyword.get(0).getName().contains(keyword));
    }
}