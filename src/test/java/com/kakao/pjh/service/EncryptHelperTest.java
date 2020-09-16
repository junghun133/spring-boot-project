package com.kakao.pjh.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EncryptHelperTest {
    private static EncryptHelper encrypt;

    @BeforeAll
    public static void setUp(){
        encrypt = new EncryptHelper();
    }
/*

    private static Stream<Arguments> makePassword() { // argument source method
        return Stream.of(
                Arguments.of("user1", "user1password"),
                Arguments.of("user2", "user2password"),
                Arguments.of("user3", "user3password"),
                Arguments.of("user4", "user4password"),
                Arguments.of("user5", "user5password"),
                Arguments.of("user6", "user6password")
        );
    }

    @ParameterizedTest
    @MethodSource("makePassword")
    public void makeUserPassword(String id, String password){
        String encryptPassword = EncryptHelperTest.encrypt.encrypt(password);
        System.out.printf("id = %s password = %s%n", id, encryptPassword);
    }
*/

    private static Stream<Arguments> isAvailablePasswordParameter() { // argument source method
        return Stream.of(
                Arguments.of("user1", "user1password", "$2a$10$Q3SoMTqcw7R8NTeHpt/XwujehOYM7nyv/kuSzFzZZXAp8Ml36w6Va"),
                Arguments.of("user2", "user2password", "$2a$10$YY4KleuCC3c/xI3auCQkfeVgQ7SXSFj59GgKXL8sGJ08ItwNSCKti"),
                Arguments.of("user3", "user3password", "$2a$10$Mk5yH1kKdsaOmCZ/GryM7evBWMkv.TGwMpruaclxyxSqo/JAxAke6"),
                Arguments.of("user4", "user4password", "$2a$10$DbKYF0vI08yPRVlyvxC5iugzeig3X5Qn4/lHo47VdcJlvNkaRxj5."),
                Arguments.of("user5", "user5password", "$2a$10$jaNdA7YoZKdXeWws9DE.Cu2hdxqzjlslQ2i4mrxeCk2RDXPuxAC7u"),
                Arguments.of("user6", "user6password", "$2a$10$LxyNs9rlAYlGTDjxtLIlCOFVWHHq7iiqj8A2IV6g0JACTNKW69kve")
        );
    }

    @ParameterizedTest
    @MethodSource("isAvailablePasswordParameter")
    public void encryptAndDecryptTest(String id, String password, String encryptPwd){
        boolean match = encrypt.isMatch(password, encryptPwd);
        assertThat(match).isTrue();
    }
}