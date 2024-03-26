package com.velikanovdev.sportcenterplatform.service.impl;

import com.velikanovdev.sportcenterplatform.dto.UserDTO;
import com.velikanovdev.sportcenterplatform.entity.User;
import com.velikanovdev.sportcenterplatform.repository.UserRepository;
import com.velikanovdev.sportcenterplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long registerUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByEmail(userDTO.email());

        if(userOptional.isPresent()) {
            return null;
        }

        User user = new User(userDTO.email(), userDTO.username(), userDTO.password());

        return userRepository.save(user).getId();
    }
}
