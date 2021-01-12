package com.pjh.web.shop.transformer;

import com.pjh.web.shop.domain.AttachmentImage;
import com.pjh.web.shop.domain.ProductMaster;
import com.pjh.web.shop.dto.ProductAddRequest;

public class ProductAddTransformerImpl implements Transformer<ProductMaster, ProductAddRequest> {

    @Override
    public ProductMaster transform(ProductAddRequest source) {
        ProductMaster productMaster = new ProductMaster();
        productMaster.setId(source.getId());
        productMaster.setName(source.getName());
        productMaster.setPrice(source.getPrice());
        //...
        AttachmentImage attachmentImage = new AttachmentImage();
        attachmentImage.setId(source.getAttachmentImageId());
        productMaster.setAttachmentImage(attachmentImage);

        return productMaster;
    }
}
