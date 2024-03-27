package com.velikanovdev.sportcenterplatform.service;

import com.velikanovdev.sportcenterplatform.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Long registerUser(UserDTO userDTO);
}
