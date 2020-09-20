package com.kakao.pjh.dao;

import com.kakao.pjh.data.entity.Keyword;
import com.kakao.pjh.data.entity.Map;

import java.util.List;

public interface MapSearchDao {
    void insertMapRowData(Map map);
    Map selectMapData(Long mapId);
    List<Keyword> selectPopularKeyword();
    void mergeIntoKeyword(Keyword keyword);

    Boolean selectKeywordTotalCount(String keyword);
}
