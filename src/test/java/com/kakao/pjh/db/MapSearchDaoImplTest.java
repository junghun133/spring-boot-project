package com.kakao.pjh.db;

import com.kakao.pjh.dao.MapSearchDaoImpl;
import com.kakao.pjh.data.entity.Keyword;
import com.kakao.pjh.data.entity.Map;
import com.kakao.pjh.exception.UserNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MapSearchDaoImplTest {
    @Mock
    MapRepository mapRepository;
    @Mock
    KeywordRepository keywordRepository;
    @InjectMocks
    MapSearchDaoImpl mapSearchDao;

    final String SUCC_KEYWORD = "sc";
    final String SUCC_KEYWORD_BUT_COUNT_ZERO = "scZERO";
    final String FAIL_KEYWORD = "fa";

    final Long SUCC_Long = Long.valueOf(1);
    final Long FAIL_Long = Long.valueOf(0);

    @Disabled
    @Test
    void insertMapRowData() {
        Map failMap = new Map();
        failMap.setMapId(Long.valueOf(0));
        when(mapRepository.findByMapId(FAIL_Long)).thenReturn(Optional.ofNullable(null));
    }

    @Test
    void selectMapData() {
        Map map = new Map();
        when(mapRepository.findByMapId(FAIL_Long)).thenReturn(Optional.ofNullable(null));
        when(mapRepository.findByMapId(SUCC_Long)).thenReturn(Optional.of(map));
        assertThat(mapSearchDao.selectMapData(SUCC_Long)).isEqualTo(map);
        assertThat(mapSearchDao.selectMapData(FAIL_Long)).isNull();
    }

    @Test
    void selectKeywordTotalCountTest(){
        Keyword keyword = new Keyword();
        keyword.setSearchedTotalCount(1);
        Keyword keywordTotalZero = new Keyword();
        keyword.setSearchedTotalCount(0);
        when(keywordRepository.findByKeyword(FAIL_KEYWORD)).thenReturn(Optional.ofNullable(null));
        when(keywordRepository.findByKeyword(SUCC_KEYWORD)).thenReturn(Optional.of(keyword));
        when(keywordRepository.findByKeyword(SUCC_KEYWORD_BUT_COUNT_ZERO)).thenReturn(Optional.of(keywordTotalZero));
        assertThat(mapSearchDao.selectKeywordTotalCount(SUCC_KEYWORD)).isFalse();
        assertThat(mapSearchDao.selectKeywordTotalCount(FAIL_KEYWORD)).isTrue();
        //TODO Test중 발견 keyword가 있으나 total count가 0일경우 NPE발생 가능성 있음
        assertThrows(NullPointerException.class, () -> mapSearchDao.selectKeywordTotalCount(SUCC_KEYWORD_BUT_COUNT_ZERO));
    }

}