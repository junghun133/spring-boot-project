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
     * 상품 총 개수 조회
     * @return
     */
    public int findAllProductCount(){
        return (int) productMasterRepository.count();
    }
}
