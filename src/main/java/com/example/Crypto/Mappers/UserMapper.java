package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.UserDTO;
import com.example.Crypto.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "username", source = "userDTO.username")
    User convertDtoToEntity(UserDTO userDTO);

    @Mapping(target = "username",source = "user.username")
    UserDTO convertEntityToDto(User user);
}

