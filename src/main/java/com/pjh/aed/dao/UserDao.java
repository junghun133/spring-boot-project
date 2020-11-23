package com.pjh.aed.dao;

import com.pjh.aed.data.domain.User;
import com.pjh.aed.exception.UserNotFoundException;
import com.pjh.aed.jpa.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        List<User> user = userRepository.selectUserId(id);
        if(user.isEmpty())
            throw new UserNotFoundException();

        return user.get(0);
    }

    public User loginUser(String id, String password) throws UserNotFoundException {
        if(id == null)
            throw new UserNotFoundException();

        List<User> user = userRepository.selectUserIdAndPassword(id, password);
        if(user.isEmpty())
            throw new UserNotFoundException();

        return user.get(0);
    }
}
