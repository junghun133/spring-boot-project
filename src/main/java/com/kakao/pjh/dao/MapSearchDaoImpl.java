package com.kakao.pjh.dao;

import com.kakao.pjh.data.entity.MapData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MapSearchDaoImpl implements MapSearchDao{

    @Override
    public void mergeIntoMapRowData(MapData mapData) {

    }

    @Override
    public MapData selectMapData(Long id) {
        return null;
    }

    @Override
    public List<MapData> selectPopularData() {
        return null;
    }
}
