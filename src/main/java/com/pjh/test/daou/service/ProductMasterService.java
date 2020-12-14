package com.pjh.test.daou.service;

import com.pjh.test.daou.controller.ProductListForm;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.exception.BadRequestProductException;
import com.pjh.test.daou.repository.ProductMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductMasterService {
    private final ProductMasterRepository productMasterRepository;
    private final int DEFAULT_LIST_COUNT = 9;


    @Transactional
    public void saveProduct(ProductMaster productMaster){
        productMasterRepository.save(productMaster);
    }

    public List<ProductMaster> findProducts(){
        return productMasterRepository.findAll();
    }
    /**
     * 상품 총 개수 조회
     * @return
     */
    public int findAllProductCount(){
        return (int) productMasterRepository.count();
    }

    /**
     * 상품 List 뿌리기 조회
     */
    public List<ProductMaster> findProductsPaging(ProductListForm productListForm){
        if(productListForm == null){
            throw new BadRequestProductException();
        }
        return productMasterRepository.selectProductList(productListForm.getProductId(), productListForm.getPageSize(), productListForm.getProduct());
    }
}
