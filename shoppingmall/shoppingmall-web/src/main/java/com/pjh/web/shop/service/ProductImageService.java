package com.pjh.web.shop.service;

import com.pjh.web.shop.domain.AttachmentImage;
import com.pjh.web.shop.repository.AttachmentImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductImageService {
    private final AttachmentImageRepository attachmentImageRepository;

    public List<AttachmentImage> getImageList(){
        return attachmentImageRepository.findAllByOrderByEnabledAsc();
    }
}
