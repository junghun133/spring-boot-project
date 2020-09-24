package com.kakao.pjh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.pjh.data.dto.user.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    String succ_loginData;
    String fail_loginData;

    @Before
    public void setup() throws JsonProcessingException {
        mockMvc = webAppContextSetup(webApplicationContext)
                .build();


        UserDto succ_userDto = UserDto.userBuilder().id("user1").password("user1password").build();
        succ_loginData =  objectMapper.writeValueAsString(succ_userDto);
        UserDto fail_userDto = UserDto.userBuilder().id("user1").password("user1password1").build();
        fail_loginData =  objectMapper.writeValueAsString(fail_userDto);
    }

    @Test
    public void userLoginTest() throws Exception {
        mockMvc.perform(post("/v1/user/login")
                .accept(MediaType.APPLICATION_JSON)
                .content(succ_loginData)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$['message']", containsString("성공")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['id']", containsString("user1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['name']", containsString("roy")))
                .andDo(print());
    }

    @Test
    public void userLoginFailTest() throws Exception {
        mockMvc.perform(post("/v1/user/login")
                .accept(MediaType.APPLICATION_JSON)
                .content(fail_loginData)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$['message']", containsString("비밀번호가 맞지않습니다")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['details']", containsString("uri=/v1/user/login")))
                .andDo(print());
    }
}
