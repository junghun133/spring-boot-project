package com.pjh.test.daou.service;

import com.pjh.test.daou.domain.AttachmentImage;
import com.pjh.test.daou.repository.AttachmentImageRepository;
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
