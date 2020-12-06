package com.pjh.webflux_api.router;

import com.pjh.webflux_api.dto.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Component
public class UserRouterFunctionHandler {

    //searchuser::Usercontroller 기능 동일
    public Mono<ServerResponse> userSearch(ServerRequest request) {
        Mono<UserDto> userDtoMono = Mono.just(new UserDto(1L, "hello", "hi"));
        return ServerResponse.ok().body(userDtoMono, UserRouterFunctionHandler.class);
    }

    @Configuration
    @EnableWebFlux
    public class WebConfig implements WebFluxConfigurer {
        @Bean
        public RouterFunction<ServerResponse> routes(UserRouterFunctionHandler handler) {
            return RouterFunctions.route(GET("/hello"), handler::userSearch);
        }
    }
}
