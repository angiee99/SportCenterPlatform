package com.velikanovdev.sportcenterplatform.controller;

import com.velikanovdev.sportcenterplatform.dto.UserDTO;
import com.velikanovdev.sportcenterplatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        Long id = userService.registerUser(userDTO);
        if(id == null) {
            return ResponseEntity.badRequest().body("User with email " + userDTO.email() + " already exists");
        }
        return ResponseEntity.ok("User with id " + id + " successfully registered");
    }
}
