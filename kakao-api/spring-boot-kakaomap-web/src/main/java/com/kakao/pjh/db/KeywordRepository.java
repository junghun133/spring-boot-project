package com.kakao.pjh.db;

import com.kakao.pjh.data.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findByKeyword(String keyword);

    List<Keyword> findTop10HitCntByOrderByHitCntDesc();
}
