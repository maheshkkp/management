package com.job.management.dto;

import com.job.management.domain.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;
}
