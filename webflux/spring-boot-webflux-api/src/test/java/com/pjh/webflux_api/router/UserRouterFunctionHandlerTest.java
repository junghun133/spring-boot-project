package com.pjh.webflux_api.router;

import com.pjh.webflux_api.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebFluxTest
@Import(value = UserRouterFunctionHandler.class)
class UserRouterFunctionHandlerTest {

    @Autowired
    WebTestClient webTestClient;


    @Test
    public void test_UserSearchRouter() {
        // WebClient로 "hello"라는 URL을 호출
        UserDto responseBody = webTestClient.get().uri("/search").exchange()
                .expectStatus().isOk() // 응답이 200인지 확인
                .expectBody(UserDto.class) // 리턴하는 객체가 HelloWorld 클래스인지 확인
                .returnResult().getResponseBody();

        // reponse된 객체에 원하는 결과값이 들어있는지 assert함.
        assertThat(responseBody.getId()).isEqualTo(1);
        assertThat(responseBody.getUser_id()).isEqualTo("jh");
        assertThat(responseBody.getUser_pwd()).isEqualTo("1234");
    }

    @Test
    public void test_FailExecutePostMethod() {
        // POST는 지원하지 않는 method
        webTestClient.post().uri("/search").exchange()
                .expectStatus().isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    }
}