package com.pjh.aed.service;

import com.pjh.aed.data.dto.UserDto;
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

    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        User save = userRepository.save(user);

        UserDto savedUser = new UserDto();
        savedUser.setId(save.getId());
        savedUser.setName(save.getName());
        return savedUser;
    }

    public UserDto findOne(String id) throws UserNotFoundException {
        if(id == null)
            throw new UserNotFoundException();

        //id가 아니라 usercode로 조회해야함
        Optional<User> selectedUser = userRepository.findById(Long.parseLong(id));
        User user = selectedUser.orElse(null);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }
}
