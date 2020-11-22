package com.pjh.aed.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjh.aed.data.dto.UserBindData;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserControllerTest {
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    String succ_loginData;
    String fail_loginData;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext)
                .build();

        UserBindData succ_login = UserBindData.userBindDataBuilder().id("pjh").name("junghoon").password("1234").build();
        succ_loginData =  objectMapper.writeValueAsString(succ_login);
        UserBindData fail_login = UserBindData.userBindDataBuilder().id("pjh").name("junghoon").password("1234").build();
        fail_loginData =  objectMapper.writeValueAsString(fail_login);

        mockMvc.perform(post("/user/v1/create/user")
                .accept(MediaType.APPLICATION_JSON)
                .content(succ_loginData)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    //유저 조회 테스트 : succ
    @org.junit.Test
    public void userLoginTest() throws Exception {
        mockMvc.perform(get("/user/v1/search/pjh"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$['code']", containsString("성공")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['id']", containsString("pjh")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['name']", containsString("junghoon")))
                .andDo(print());
    }

    //유저 조회 테스트 : fail
    @org.junit.Test
    public void userLoginFailTest() throws Exception {
        mockMvc.perform(get("/user/v1/search/unknown"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$['message']", containsString("조회된 유저가 없습니다.")))
                .andDo(print());
    }

    //유저 토큰 생성 API 테스트
    @org.junit.Test
    public void userCreateTokenTest() throws Exception {
        UserBindData userBindData = new UserBindData();
        userBindData.setId("pjh");
        userBindData.setPassword("1234");
        String loginData = objectMapper.writeValueAsString(userBindData);
        mockMvc.perform(post("/user/v1/create/token")
                .accept(MediaType.APPLICATION_JSON)
                .content(loginData)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$['code']", containsString("성공")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['id']", containsString("pjh")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['name']", containsString("junghoon")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['token']", notNullValue()))
                .andDo(print());
    }
}
