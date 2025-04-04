package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.UserDTO;
import com.example.Crypto.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "username", source = "userDto.username")
    @Mapping(target = "accountBalance", source = "userDto.account_balance")
    @Mapping(target = "id",source = "userId")
    User convertDtoToEntity(UserDTO userDTO, Long userId);

    @Mapping(target = "username",source = "user.username")
    @Mapping(target = "accountBalance",source = "user.balance")
    UserDTO convertEntityToDto(User user);
}

