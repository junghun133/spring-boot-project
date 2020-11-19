package com.pjh.aed.dao;

import com.pjh.aed.data.entity.User;
import com.pjh.aed.exception.UserNotFoundException;
import com.pjh.aed.jpa.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDao {
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User isUser(String id) throws UserNotFoundException {
        if(id == null)
            throw new UserNotFoundException();

        //id가 아니라 usercode로 조회해야함
        Optional<User> selectedUser = userRepository.findById(id);
        return selectedUser.orElse(null);
    }

    public User loginUser(String id) throws UserNotFoundException {
        if(id == null)
            throw new UserNotFoundException();

        //id가 아니라 usercode로 조회해야함
        Optional<User> selectedUser = userRepository.findById(id);
        return selectedUser.orElse(null);
    }
}
