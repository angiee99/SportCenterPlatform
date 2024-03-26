package com.velikanovdev.sportcenterplatform.service;

import com.velikanovdev.sportcenterplatform.dto.UserDTO;
import com.velikanovdev.sportcenterplatform.entity.User;
import com.velikanovdev.sportcenterplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long registerUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByEmail(userDTO.email());

        if(userOptional.isPresent()) {
            return null;
        }

        User user = new User();
        user.setEmail(userDTO.email());
        user.setName(userDTO.username());
        user.setPassword(userDTO.password());

        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }
}
