package com.kakao.pjh.dao;

import com.kakao.pjh.data.entity.Keyword;
import com.kakao.pjh.data.entity.Map;
import com.kakao.pjh.db.KeywordRepository;
import com.kakao.pjh.db.MapRepository;
import com.kakao.pjh.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    //가장 검색 빈도수가 많은 10건의 keyword 조회
    @Override
    public List<Keyword> selectPopularKeyword() {
        return keywordRepository.findTop10HitCntByOrderByHitCntDesc();
    }

    //Keyword data 존재시 update, 존재하지않으면 save
    @Override
    public void mergeIntoKeyword(Keyword keyword) {
        Optional<Keyword> selectedKeyword = keywordRepository.findByKeyword(keyword.getKeyword());
        if(selectedKeyword.isPresent()){
            Keyword k = selectedKeyword.get();
            int hitCnt = k.getHitCnt();
            k.setHitCnt(hitCnt + 1);
            k.setSearchedDate(new Date());
        }else{
            keyword.setSearchedDate(new Date());
            keywordRepository.save(keyword);
        }
    }

    //return false = exception
    @Override
    public Boolean selectKeywordTotalCount(String keyword) {
        Optional<Keyword> byKeyword = keywordRepository.findByKeyword(keyword);

        if(!byKeyword.isPresent())
            return true;
        else{ //TODO totalcount 0이고 데이터 갱신 날짜 (ex 86400) 비교필요
            return dateValidate(byKeyword.get());
        }
    }
    public boolean dateValidate(Keyword keyword){
        int totalCount = keyword.getSearchedTotalCount();
        if(totalCount == 0){
            return DateUtil.compareDate(keyword.getSearchedDate());
        }
        return true;
    }
}
