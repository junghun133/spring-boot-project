package com.kakao.pjh.dao;

import com.kakao.pjh.data.entity.MapData;

import java.util.List;

public interface MapSearchDao {
    void mergeIntoMapRowData(MapData mapData);
    MapData selectMapData(Long id);
    List<MapData> selectPopularData();
}
