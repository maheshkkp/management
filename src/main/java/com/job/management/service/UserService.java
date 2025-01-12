package com.job.management.service;

import com.job.management.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}