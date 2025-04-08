package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.UserDTO;
import com.example.Crypto.Entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-08T14:33:29+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User convertDtoToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userDTO.username() );

        return user;
    }

    @Override
    public UserDTO convertEntityToDto(User user) {
        if ( user == null ) {
            return null;
        }

        String username = null;

        username = user.getUsername();

        UserDTO userDTO = new UserDTO( username );

        return userDTO;
    }
}
