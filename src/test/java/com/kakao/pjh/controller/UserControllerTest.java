package com.kakao.pjh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.pjh.data.dto.user.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserController userController;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @Disabled
    public void userLoginTest() throws Exception {
        UserDto userDto = UserDto.userBuilder().id("user1").password("user1password").build();
        String loginData =  objectMapper.writeValueAsString(userDto);

        mockMvc.perform(post("/v1/user/login").accept(MediaType.APPLICATION_JSON).content(loginData).contentType(MediaType.APPLICATION_JSON)).andDo(print());
    }
}
