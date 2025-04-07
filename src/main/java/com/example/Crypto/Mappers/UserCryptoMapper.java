package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.UserCryptoDTO;
import com.example.Crypto.Entities.IntermediaryEntities.UserCrypto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserCryptoMapper {
    //@Mapping(target = "userId", source = "user.id")
    //@Mapping(target = "cryptoId", source = "crypto.id")
    //@Mapping(expression = "java(new UserCryptoId(dto.getUserId(), dto.getCryptoId()))", target = "id")
    //UserCrypto ConvertDtoToEntity(UserCryptoDTO userCryptoDTO);

    @Mapping(target = "userId",source = "user.id")
    @Mapping(target = "cryptoId",source = "crypto.id")
    @Mapping(target = "username",source = "user.username")
    @Mapping(target = "cryptoName",source = "crypto.cryptoName")
    UserCryptoDTO ConvertEntityToDto(UserCrypto userCrypto);

}
