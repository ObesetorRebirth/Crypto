package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.UserCryptoDTO;
import com.example.Crypto.Entities.Crypto;
import com.example.Crypto.Entities.IntermediaryEntities.UserCrypto;
import com.example.Crypto.Entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-08T14:33:29+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class UserCryptoMapperImpl implements UserCryptoMapper {

    @Override
    public UserCryptoDTO ConvertEntityToDto(UserCrypto userCrypto) {
        if ( userCrypto == null ) {
            return null;
        }

        Long userId = null;
        Long cryptoId = null;
        String username = null;
        String cryptoName = null;
        Double quantity = null;

        userId = userCryptoUserId( userCrypto );
        cryptoId = userCryptoCryptoId( userCrypto );
        username = userCryptoUserUsername( userCrypto );
        cryptoName = userCryptoCryptoCryptoName( userCrypto );
        quantity = userCrypto.getQuantity();

        UserCryptoDTO userCryptoDTO = new UserCryptoDTO( userId, cryptoId, username, cryptoName, quantity );

        return userCryptoDTO;
    }

    private Long userCryptoUserId(UserCrypto userCrypto) {
        if ( userCrypto == null ) {
            return null;
        }
        User user = userCrypto.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long userCryptoCryptoId(UserCrypto userCrypto) {
        if ( userCrypto == null ) {
            return null;
        }
        Crypto crypto = userCrypto.getCrypto();
        if ( crypto == null ) {
            return null;
        }
        Long id = crypto.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String userCryptoUserUsername(UserCrypto userCrypto) {
        if ( userCrypto == null ) {
            return null;
        }
        User user = userCrypto.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String userCryptoCryptoCryptoName(UserCrypto userCrypto) {
        if ( userCrypto == null ) {
            return null;
        }
        Crypto crypto = userCrypto.getCrypto();
        if ( crypto == null ) {
            return null;
        }
        String cryptoName = crypto.getCryptoName();
        if ( cryptoName == null ) {
            return null;
        }
        return cryptoName;
    }
}
