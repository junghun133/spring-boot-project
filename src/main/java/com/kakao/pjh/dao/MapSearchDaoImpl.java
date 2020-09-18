package com.kakao.pjh.dao;

import com.kakao.pjh.data.entity.Map;
import com.kakao.pjh.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class MapSearchDaoImpl implements MapSearchDao{
    @Autowired
    MapRepository mapRepository;

    @Override
    public void mergeIntoMapRowData(Map map) {
        Optional<Map> selectedMap = mapRepository.findByMapId(map.getMapId());
        if(selectedMap.isPresent()){
            Map getSelectedMap = selectedMap.get();
            Integer hitCount = getSelectedMap.getHitCnt();
            getSelectedMap.setHitCnt(hitCount + 1);
        }else{
            mapRepository.save(map);
        }
    }

    @Override
    public Map selectMapData(Long mapId) {
        Optional<Map> map = mapRepository.findByMapId(mapId);
        return map.orElse(null);
    }

    @Override
    public List<Map> selectPopularData() {
        return null;
    }
}
