package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.UserDTO;
import com.example.Crypto.Entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-08T00:10:25+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User convertDtoToEntity(UserDTO userDTO, Long userId) {
        if ( userDTO == null && userId == null ) {
            return null;
        }

        User user = new User();

        if ( userDTO != null ) {
            user.setUsername( userDTO.username() );
            user.setBalance( userDTO.balance() );
        }
        user.setId( userId );

        return user;
    }

    @Override
    public UserDTO convertEntityToDto(User user) {
        if ( user == null ) {
            return null;
        }

        String username = null;
        Double balance = null;

        username = user.getUsername();
        balance = user.getBalance();

        UserDTO userDTO = new UserDTO( username, balance );

        return userDTO;
    }
}
