package com.pjh.aed.service;

import com.pjh.aed.data.dto.UserBindData;
import com.pjh.aed.data.dto.res.UserProcessResponse;
import com.pjh.aed.data.entity.User;
import com.pjh.aed.exception.UserNotFoundException;
import com.pjh.aed.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDaoService implements DaoService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserProcessResponse findOne(String id) throws UserNotFoundException {
        if(id == null)
            throw new UserNotFoundException();

        //id가 아니라 usercode로 조회해야함
        Optional<User> selectedUser = userRepository.findById(Long.parseLong(id));
        User user = selectedUser.orElse(null);

        UserProcessResponse userBindData = new UserProcessResponse();
        userBindData.setId(user.getId());
        userBindData.setName(user.getName());
        return userBindData;
    }
}
