package com.pjh.test.daou.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjh.test.daou.controller.ProductForm;
import com.pjh.test.daou.controller.ProductListForm;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.ProductModifyHistory;
import com.pjh.test.daou.domain.product.ProductFactory;
import com.pjh.test.daou.domain.product.ProductType;
import com.pjh.test.daou.exception.BadRequestProductException;
import com.pjh.test.daou.exception.NotFoundProductException;
import com.pjh.test.daou.repository.ProductMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductMasterService {
    private final ProductMasterRepository productMasterRepository;
    private final ProductHistoryManager productHistoryManager;

    //상품 저장
    @Transactional
    public void saveProduct(ProductMaster productMaster){
        productMaster.setRegistrationDate(LocalDateTime.now());
        productMasterRepository.save(productMaster);
    }

    //상품 수정
    @Transactional
    public void updateProduct(ProductForm productForm){
        StringBuilder updateLogSB = new StringBuilder();

        Optional<ProductMaster> productOptional = productMasterRepository.findById(productForm.getId());
        ProductMaster productObject = productOptional.orElseThrow(NotFoundProductException::new);
        ProductType productType = ProductType.convertProductType(productForm.getProductType());

        updateLogSB.append("before||");
        updateLogSB.append(productHistoryManager.convertPojo2Json(productObject));

        //dirty checking
        ProductFactory.createFormToProductObject(productType, productForm, productObject);

        updateLogSB.append("||update||");
        updateLogSB.append(productHistoryManager.convertPojo2Json(productObject));

        // 상품 변경내역 히스토리
        ProductModifyHistory productModifyHistory = new ProductModifyHistory();
        productModifyHistory.setModifiedDate(LocalDateTime.now());
        productModifyHistory.setModifiedLog(updateLogSB.toString());

        //cascade
        productObject.addModifyHistory(productModifyHistory);
    }

    //상품 삭제
    @Transactional
    public void deleteProduct(Long productId) {
        productMasterRepository.deleteById(productId);
    }

    // 상품 단건 조회
    public ProductMaster findProduct(Long productId){
        Optional<ProductMaster> selectedProduct = productMasterRepository.findById(productId);
        return selectedProduct.orElseThrow(NotFoundProductException::new);
    }

    // 상품 전체 조회 (조건 미포함)
    public List<ProductMaster> findProducts(){
        return productMasterRepository.findAll();
    }

    //상품 총 개수 조회
    public int findAllProductCount(){
        return (int) productMasterRepository.count();
    }

    //상품 전체 조회(조건 포함)
    public List<ProductMaster> findProductsPaging(ProductListForm productListForm){
        if(productListForm == null){
            throw new BadRequestProductException();
        }
        return productMasterRepository.selectProductList(productListForm.getProductId(), productListForm.getPageSize(), productListForm.getProduct());
    }

}