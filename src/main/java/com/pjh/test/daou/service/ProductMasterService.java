package com.pjh.test.daou.service;

import com.pjh.test.daou.controller.form.ProductForm;
import com.pjh.test.daou.controller.form.ProductListForm;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.ProductModifyHistory;
import com.pjh.test.daou.domain.product.ProductFactory;
import com.pjh.test.daou.domain.ProductModifyHistoryManager;
import com.pjh.test.daou.domain.product.ProductType;
import com.pjh.test.daou.exception.BadRequestProductException;
import com.pjh.test.daou.exception.InternalServerException;
import com.pjh.test.daou.exception.NotFoundProductException;
import com.pjh.test.daou.repository.ProductMasterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProductMasterService {
    private final int CONTENT_LIMIT = 9;
    private final ProductMasterRepository productMasterRepository;
    private final ProductModifyHistoryManager productModifyHistoryManager;

    //상품 저장
    @Transactional
    public void saveProduct(ProductMaster productMaster){
        productMaster.setRegistrationDate(LocalDateTime.now());
        productMasterRepository.save(productMaster);
    }

    //상품 수정
    @Transactional
    public void updateProduct(ProductForm productForm){
        Optional<ProductMaster> productOptional = productMasterRepository.findById(productForm.getId());
        ProductMaster productObject = productOptional.orElseThrow(NotFoundProductException::new);
        ProductType productType = ProductType.convertProductType(productForm.getProductType());
        Object copy = productObject.clone(); // 저장전 상품정보 deep copy

        //dirty checking
        ProductFactory.updateFormToProductObject(productType, productForm, productObject);

        // 상품 변경내역 히스토리
        List<String> difference = null;
        try {
            difference = productModifyHistoryManager.getDifference(copy, productObject);
        } catch (IllegalAccessException e) {
            throw new InternalServerException(e);
        }

        ProductModifyHistory productModifyHistory = new ProductModifyHistory();
        productModifyHistory.setModifiedDate(LocalDateTime.now());
        productModifyHistory.setModifiedLog(productModifyHistoryManager.convertPojo2Json(difference));

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
    public List<ProductMaster> findProducts(ProductListForm productListForm){
        if(productListForm == null){
            throw new BadRequestProductException();
        }

        return productMasterRepository.selectProductList(productListForm.getProductId(), 0, CONTENT_LIMIT, productListForm.getProduct());
    }

    public List<ProductMaster> findProductsPaging(ProductListForm productListForm){

        //offset, limit 처리
        int page = productListForm.getPage();
        int limit = CONTENT_LIMIT * page;

        return productMasterRepository.selectProductList(productListForm.getProductId(), 0, limit, productListForm.getProduct());
    }

    public String saveImageFile(MultipartFile productImage) {
        UUID uuid = UUID.randomUUID();
        String imageName = productImage.getOriginalFilename() + "_" + uuid;
        Path filePath = Paths.get("classpath:resources/static/images/"+productImage.getOriginalFilename());
        try {
            FileOutputStream output = new FileOutputStream("/static/images/"+productImage.getOriginalFilename());
            output.write(productImage.getBytes());
//            Files.write(filePath, productImage.getBytes());
        } catch (IOException e) {
            log.error("Fail save image case:" + e.getMessage());
            e.printStackTrace();
        }

        return filePath.toString();
    }
}