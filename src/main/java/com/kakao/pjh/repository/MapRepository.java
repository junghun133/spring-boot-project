package com.kakao.pjh.repository;

import com.kakao.pjh.data.entity.MapData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<MapData, Long> {
}
