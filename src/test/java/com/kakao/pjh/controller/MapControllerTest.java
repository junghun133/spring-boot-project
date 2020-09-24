package com.kakao.pjh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.pjh.dao.UserDaoImpl;
import com.kakao.pjh.service.MapSearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MapControllerTest {
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    @InjectMocks
    MapSearchService mapSearchService;
    @MockBean
    UserDaoImpl userDao;

    @Before
    public void setup(){
        mockMvc = webAppContextSetup(webApplicationContext)
                .build();
    }

    //전체 지도 정보 조회 성공
    @Test
    public void mapControllerRetrieveMapInfoSuccTest() throws Exception {
        doNothing().when(userDao).apiKeyValidation("test");

        mockMvc.perform(get("/v1/map/search/keyword?query=kakao")
                .header("Authorization", "test")
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$['message']", containsString("성공")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['meta']", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['meta'].total_count", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['meta'].pageable_count", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['meta'].is_end", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents']", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents'][0].id", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents'][0].place_name", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents'][0].category_name", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents'][0].road_address_name", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['documents'][0].detailUri", is(notNullValue())))
        .andDo(print());
    }

    //결과값없는 키워드 테스트
    @Test
    public void mapControllerRetrieveMapInfoNoResultFailTest() throws Exception {
        doNothing().when(userDao).apiKeyValidation("test");

        mockMvc.perform(get("/v1/map/search/keyword?query=박정훈지원자")
                .header("Authorization", "test")
        )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    //상세 지도 정보 조회 성공
    @Test
    public void mapControllerDetailMapInfoSuccTest() throws Exception {
        doNothing().when(userDao).apiKeyValidation("test");

        mockMvc.perform(get("/v1/map/search/keyword/detail/18577297?mapUrlType=map")
                .header("Authorization", "test")
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$['message']", containsString("성공")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['id']", containsString("18577297")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['place_name']", containsString("카카오 판교오피스")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['category_name']", containsString("서비스,산업 > 인터넷,IT > 포털서비스")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['category_group_code']", containsString("")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['category_group_name']", containsString("")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['phone']", containsString("1899-1326")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['address_name']", containsString("경기 성남시 분당구 삼평동 681")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['road_address_name']", containsString("경기 성남시 분당구 판교역로 235")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['place_url']", containsString("http://place.map.kakao.com/1857729")))
                .andDo(print());
    }
    //인기 검색순위
    @Test
    public void mapControllerPopularKeywordSuccTest() throws Exception {
        doNothing().when(userDao).apiKeyValidation("test");

        mockMvc.perform(get("/v1/map/popular")
                .header("Authorization", "test")
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$['message']", containsString("성공")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['popularKeywords']", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['popularKeywords']", hasSize(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$['popularKeywords'][0].keyword", containsString("카카오뱅크")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['popularKeywords'][0].searchedTotalCount", is(45)))
                .andExpect(MockMvcResultMatchers.jsonPath("$['popularKeywords'][0].hitCnt", is(10)))
                .andDo(print());
    }
}