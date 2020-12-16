package com.pjh.test.daou.repository;

import com.pjh.test.daou.domain.AttachmentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentImageRepository extends JpaRepository<AttachmentImage, Long> {
    List<AttachmentImage> findAllByOrderByEnabledAsc();
}
