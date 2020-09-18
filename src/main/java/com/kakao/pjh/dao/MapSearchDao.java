package com.kakao.pjh.dao;

import com.kakao.pjh.data.entity.Map;

import java.util.List;

public interface MapSearchDao {
    void mergeIntoMapRowData(Map map);
    Map selectMapData(Long mapId);
    List<Map> selectPopularData();
}
