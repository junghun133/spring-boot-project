package com.pjh.web.shop.repository.dsl;

import com.pjh.web.shop.repository.ProductMasterRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ProductMasterRepositoryImplTest {
    @Autowired
    ProductMasterRepository productMasterRepository;


}