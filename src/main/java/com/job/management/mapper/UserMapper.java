package com.job.management.mapper;

import com.job.management.domain.User;
import com.job.management.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BasicMapper<User, UserDTO> {
}