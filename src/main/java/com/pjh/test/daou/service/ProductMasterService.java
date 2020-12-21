package com.pjh.test.daou.service;

import com.pjh.test.daou.controller.form.ProductForm;
import com.pjh.test.daou.http.entity.Documents;
import com.pjh.test.daou.http.entity.Meta;
import com.pjh.test.daou.http.entity.ProductResponse;
import com.pjh.test.daou.domain.AttachmentImage;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.domain.ProductModifyHistory;
import com.pjh.test.daou.domain.ProductModifyHistoryManager;
import com.pjh.test.daou.domain.product.ProductFactory;
import com.pjh.test.daou.domain.product.ProductType;
import com.pjh.test.daou.exception.BadRequestProductException;
import com.pjh.test.daou.exception.InternalServerException;
import com.pjh.test.daou.exception.NotFoundProductException;
import com.pjh.test.daou.repository.AttachmentImageRepository;
import com.pjh.test.daou.repository.ProductMasterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProductMasterService {
    private static final int CONTENT_LIMIT = 9;
    private final ProductMasterRepository productMasterRepository;
    private final ProductModifyHistoryManager productModifyHistoryManager;
    private final AttachmentImageRepository attachmentImageRepository;

    //상품 저장
    @Transactional
    public void saveProduct(ProductMaster productMaster, Long imageId){
        productMaster.setRegistrationDate(LocalDateTime.now());
        Optional<AttachmentImage> foundAttachment = attachmentImageRepository.findById(imageId);
        AttachmentImage attachmentImage = foundAttachment.orElseThrow(NotFoundProductException::new);
        productMaster.setAttachmentImage(attachmentImage);
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
            log.error("An error occurred while comparing history");
            log.error(e.getMessage());
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
    public List<ProductMaster> findProductsWithKeyword(String keyword) throws BadRequestProductException{
        return productMasterRepository.selectProductList(0, CONTENT_LIMIT, keyword);
    }


    public ProductResponse findProductsPageable(PageRequest pageRequest) {
        Page<ProductMaster> page = productMasterRepository.findAllWithFetch(pageRequest);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setDocuments(makeDocuments(page.toList()));
        productResponse.setMeta(
        Meta.builder()
                .total_count(page.getTotalPages())
                .pageable_count(page.getSize())
                .is_end(page.hasNext())
                .build()
        );

        return productResponse;
    }

    //entity -> response
    public List<Documents> makeDocuments(List<ProductMaster> productList){
        List<Documents> documents = new ArrayList<>();

        for (ProductMaster pr : productList) {
            Documents document = Documents.builder()
                    .id(pr.getId())
                    .name(pr.getName())
                    .explain(pr.getExplain())
                    .price(pr.getPrice())
                    .imagePath(pr.getAttachmentImage().getImagePath())
                    .build();
            documents.add(document);
        }

        return documents;
    }
}