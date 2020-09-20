package com.kakao.pjh.repository;

import com.kakao.pjh.data.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@Rollback
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    static User user;
    @BeforeAll
    public static void setUp(){
        user = new User();
        user.setId("pjh");
        user.setPassword("pjhpassword");
        user.setApikey("wi91363c25g67mt249ox");
        user.setCreateAt(new Date());
        user.setLastLoginAt(new Date());
    }
    @Test
    public void findByUserIdTest(){
        //given
        userRepository.save(user);
        //when
        Optional<User> selectedUserOptional = userRepository.findByUserId("pjh");
        //then
        assertTrue(selectedUserOptional.isPresent());
        User selectedUser = selectedUserOptional.get();
        assertThat(selectedUser.getId()).isEqualTo("pjh");
        assertThat(selectedUser.getPassword()).isEqualTo("pjhpassword");
        assertThat(selectedUser.getApikey()).isEqualTo("wi91363c25g67mt249ox");
    }

    @Test
    public void countByApiKeyTest(){
        //given
        userRepository.save(user);
        //when
        Long count = userRepository.countByApiKey("wi91363c25g67mt249ox");
        Long failCount = userRepository.countByApiKey("notfound");
        //then
        assertThat(count).isGreaterThan(0);
        assertThat(failCount).isEqualTo(0);
    }
}