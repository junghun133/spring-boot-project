package com.kakao.pjh.dao;

import com.kakao.pjh.data.entity.Keyword;
import com.kakao.pjh.data.entity.Map;
import com.kakao.pjh.repository.KeywordRepository;
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
    @Autowired
    KeywordRepository keywordRepository;

    @Override
    public void insertMapRowData(Map map) {
        Optional<Map> selectedMap = mapRepository.findByMapId(map.getMapId());
        if(!selectedMap.isPresent()){
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

    @Override
    public void mergeIntoKeyword(Keyword keyword) {
        Optional<Keyword> selectedKeyword = keywordRepository.findByKeyword(keyword.getKeyword());
        if(selectedKeyword.isPresent()){
            Keyword k = selectedKeyword.get();
            int hitCnt = k.getHitCnt();
            k.setHitCnt(hitCnt + 1);
        }else{
            keywordRepository.save(keyword);
        }
    }
}
