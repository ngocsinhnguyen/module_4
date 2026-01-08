package com.example.module4_s8_validate_form.service.impl;

import com.example.module4_s8_validate_form.dto.UserDTO;
import com.example.module4_s8_validate_form.entity.User;
import com.example.module4_s8_validate_form.repository.UserRepository;
import com.example.module4_s8_validate_form.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserDTO userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPhonenumber(userDto.getPhonenumber());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
    }
}
