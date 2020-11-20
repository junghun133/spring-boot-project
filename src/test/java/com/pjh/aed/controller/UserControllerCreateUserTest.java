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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserControllerCreateUserTest {
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    String succ_userData;
    String fail_userData_emptyParam_id;
    String fail_userData_emptyParam_password;

    @Before
    public void setup() throws JsonProcessingException {
        mockMvc = webAppContextSetup(webApplicationContext)
                .build();

        UserBindData succ_login = UserBindData.userBindDataBuilder().id("pjh").name("junghoon").password("1234").build();
        succ_userData =  objectMapper.writeValueAsString(succ_login);

        UserBindData fail_login_withoutId = UserBindData.userBindDataBuilder().name("junghoon").password("1234").build();
        UserBindData fail_login_withoutPassword = UserBindData.userBindDataBuilder().id("pjh").name("junghoon").build();
        fail_userData_emptyParam_id =  objectMapper.writeValueAsString(fail_login_withoutId);
        fail_userData_emptyParam_password =  objectMapper.writeValueAsString(fail_login_withoutPassword);
    }

    @org.junit.Test
    public void createUserSuccessTest() throws Exception {
        mockMvc.perform(post("/user/v1/create/user")
                .accept(MediaType.APPLICATION_JSON)
                .content(succ_userData)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$['code']", containsString("성공")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['message']", containsString("")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['id']", containsString("pjh")))

                .andExpect(MockMvcResultMatchers.jsonPath("$['name']", containsString("junghoon")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.searchUser.href", containsString("http://localhost/user/v1/search/pjh")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.createToken.href", containsString("http://localhost/user/v1/create/token")))

                .andDo(print());
    }

    @org.junit.Test
    public void createUserIdParamFailTest() throws Exception {
        mockMvc.perform(post("/user/v1/create/user")
                .accept(MediaType.APPLICATION_JSON)
                .content(fail_userData_emptyParam_id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$['status']", containsString("BAD_REQUEST")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['message']", containsString("잘못된 요청입니다")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]", containsString("must not be null")))
                .andDo(print());
    }
    @org.junit.Test
    public void createUserPasswordParamFailTest() throws Exception {
        mockMvc.perform(post("/user/v1/create/user")
                .accept(MediaType.APPLICATION_JSON)
                .content(fail_userData_emptyParam_password)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$['status']", containsString("BAD_REQUEST")))
                .andExpect(MockMvcResultMatchers.jsonPath("$['message']", containsString("잘못된 요청입니다")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]", containsString("must not be null")))
                .andDo(print());
    }
}
