package com.velikanovdev.sportcenterplatform.controller;

import com.velikanovdev.sportcenterplatform.config.UserAuthenticationProvider;
import com.velikanovdev.sportcenterplatform.dto.LoginRequest;
import com.velikanovdev.sportcenterplatform.dto.LoginResponse;
import com.velikanovdev.sportcenterplatform.dto.UserDTO;
import com.velikanovdev.sportcenterplatform.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    public AuthController(UserService userService, UserAuthenticationProvider userAuthenticationProvider) {
        this.userService = userService;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            UserDTO authenticatedUser = userService.login(loginRequest.email(), loginRequest.password());
            String token = userAuthenticationProvider.createToken(authenticatedUser);
            LoginResponse response = new LoginResponse(authenticatedUser.email(), authenticatedUser.username(), token);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException | BadCredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        Long id = userService.register(userDTO);
        return ResponseEntity.ok("User with id " + id + " successfully registered");
    }
}
