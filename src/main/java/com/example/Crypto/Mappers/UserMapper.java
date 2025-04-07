package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.UserDTO;
import com.example.Crypto.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "username", source = "userDTO.username")
    @Mapping(target = "balance", source = "userDTO.balance")
    @Mapping(target = "id",source = "userId")
    User convertDtoToEntity(UserDTO userDTO, Long userId);

    @Mapping(target = "username",source = "user.username")
    @Mapping(target = "balance",source = "user.balance")
    @Mapping(target = "id",source = "user.id")
    UserDTO convertEntityToDto(User user);
}

