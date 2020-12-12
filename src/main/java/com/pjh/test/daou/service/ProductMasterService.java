package com.pjh.test.daou.service;

import com.pjh.test.daou.repository.ProductMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductMasterService {
    private final ProductMasterRepository productMasterRepository;

    /**
     * 상품 등록 갯수
     * @return
     */
    public int findAllProductCount(){
        int count = (int) productMasterRepository.count();
        return count;
    }
}
