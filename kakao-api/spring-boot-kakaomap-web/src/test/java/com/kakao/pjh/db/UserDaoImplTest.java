package com.kakao.pjh.db;

import com.kakao.pjh.dao.UserDaoImpl;
import com.kakao.pjh.data.entity.User;
import com.kakao.pjh.exception.AccessDeniedAPIKeyException;
import com.kakao.pjh.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UserDaoImplTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserDaoImpl userDao;

    final String SUCC = "sc";
    final String FAIL = "fa";
    final String FAIL_2 = "fa2";

    @Test
    public void selectUserTest(){
        User user = new User();
        when(userRepository.findByUserId(SUCC)).thenReturn(Optional.of(user));
        when(userRepository.findByUserId(FAIL)).thenReturn(Optional.ofNullable(null));

        assertThat(userDao.selectUser(SUCC)).isEqualTo(user);
        assertThrows(UserNotFoundException.class,  () -> userDao.selectUser(FAIL));
    }

    @Test
    public void apiKeyValidationTest(){
        when(userRepository.countByApiKey(FAIL)).thenReturn(Long.valueOf(0));
        when(userRepository.countByApiKey(FAIL_2)).thenReturn(Long.valueOf(-1));

        assertThrows(AccessDeniedAPIKeyException.class,  () -> userDao.apiKeyValidation(FAIL));
        assertThrows(AccessDeniedAPIKeyException.class,  () -> userDao.apiKeyValidation(FAIL_2));
    }
}