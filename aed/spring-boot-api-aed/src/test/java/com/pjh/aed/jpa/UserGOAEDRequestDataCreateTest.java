package com.pjh.aed.jpa;

import com.pjh.aed.data.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
public class UserGOAEDRequestDataCreateTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void createUserTest(){
        User user = new User();
        user.setUserCode(1L);
        user.setId("userId1");
        user.setName("userName1");

        userRepository.save(user);
        List<User> selectUser = userRepository.selectAllUserList(user);
        for (User u : selectUser) {
            System.out.println("user = " + u);
        }
    }
}