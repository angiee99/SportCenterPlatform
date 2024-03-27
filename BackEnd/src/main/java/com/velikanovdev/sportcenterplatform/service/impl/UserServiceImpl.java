package com.velikanovdev.sportcenterplatform.service.impl;

import com.velikanovdev.sportcenterplatform.config.UserAuthenticationProvider;
import com.velikanovdev.sportcenterplatform.dto.UserDTO;
import com.velikanovdev.sportcenterplatform.entity.User;
import com.velikanovdev.sportcenterplatform.repository.UserRepository;
import com.velikanovdev.sportcenterplatform.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with Email: " + email));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UserDTO(user.getEmail(), user.getUsername(), ""); // password is not needed in DTO
        } else {
            throw new BadCredentialsException("Invalid email/password combination.");
        }
    }

    public Long register(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new EntityNotFoundException("User with email " + userDTO.email() + " already exists");
        }

        String encodedPassword = passwordEncoder.encode(userDTO.password());
        User user = new User(userDTO.email(), userDTO.username(), encodedPassword);

        return userRepository.save(user).getId();
    }
}
