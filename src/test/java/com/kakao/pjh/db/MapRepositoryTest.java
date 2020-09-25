package com.kakao.pjh.db;

import com.kakao.pjh.data.entity.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
@Rollback
class MapRepositoryTest {

    @Autowired
    MapRepository mapRepository;

    static Map map;
    @BeforeAll
    public static void setUp(){
        map = new Map();
        map.setAddress_name("경기도 용인시 수지구 죽전동");
        map.setCategory_group_name("카카오API >  박정훈");
        map.setCategory_group_code("BK9");
        map.setMapId(Long.parseLong("1234"));
        map.setPhone("01027075947");
        map.setPlace_name("카카오");
        map.setPlace_url("www.kakaocorp.com");
        map.setRoad_address_name("경기도 용인시 수지구 죽전동 road");
//        failMap = Mockito.mock(Map.class);
    }

    @Test
    void findByMapId() {
        //given
        mapRepository.save(map);
        //when
        Optional<Map> success = mapRepository.findByMapId(Long.parseLong("1234"));
        Optional<Map> fail = mapRepository.findByMapId(Long.parseLong("1"));
        //then
        assertTrue(success.isPresent());
        assertFalse(fail.isPresent());
    }
}