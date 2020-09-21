package com.kakao.pjh.db;

import com.kakao.pjh.data.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MapRepository extends JpaRepository<Map, Long> {
    @Query("select m from Map m where m.mapId = :mapId")
    Optional<Map> findByMapId(@Param("mapId") Long mapId);

   /* @Query("select count(m) from Map m where m.mapId = :mapId")
    Long countById(@Param("mapId") Long mapId);*/
}

