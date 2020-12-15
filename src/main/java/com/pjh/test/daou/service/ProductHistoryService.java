package com.pjh.test.daou.service;

import com.pjh.test.daou.domain.ProductModifyHistory;
import com.pjh.test.daou.repository.ProductModifyHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductHistoryService {
    private final ProductModifyHistoryRepository repository;

    public List<ProductModifyHistory> findHistories(){
        return repository.findAll();
    }
}
