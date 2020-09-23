package com.kakao.pjh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.pjh.dao.UserDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
 class MapControllerTest {
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    MapController mapController;
    @MockBean
    UserDaoImpl userDao;

    @Before
    @Disabled
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(mapController).build();
    }

    @Test
    @Disabled
    public void mapControllerRetrieveMapInfoTest() throws Exception {
//        String user1Login = objectMapper.writeValueAsString(param);
//        Mockito.doThrow(new RuntimeException()).doNothing().when(userDao).apiKeyValidation("test");
        doNothing().when(userDao).apiKeyValidation("test");
        mockMvc.perform(MockMvcRequestBuilders.get("/search/keyword?query=kakao")
                .header("Authorization", "test")
        ).andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}