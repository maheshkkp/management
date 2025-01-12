package com.job.management.serviceimpl;

import com.job.management.domain.User;
import com.job.management.dto.UserDTO;
import com.job.management.mapper.UserMapper;
import com.job.management.repository.UserRepository;
import com.job.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(userDTO.getRoles());

        userRepository.save(user);

        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRoles(user.getRoles());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDTO.getUsername());
        user.setRoles(userDTO.getRoles());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}