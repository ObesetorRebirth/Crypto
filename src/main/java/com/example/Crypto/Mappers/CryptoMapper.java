package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.CryptoDTO;
import com.example.Crypto.Entities.Crypto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CryptoMapper {
    @Mapping(target = "cryptoName",source = "cryptoDTO.cryptoName")
    @Mapping(target = "symbol",source = "cryptoDTO.symbol")
    @Mapping(target = "currentPrice",source = "cryptoDTO.currentPrice")
    @Mapping(target = "id",source = "cryptoId")
    Crypto convertDtoToEntity(CryptoDTO cryptoDTO, Long cryptoId);

    @Mapping(target = "cryptoName",source = "crypto.cryptoName")
    @Mapping(target = "symbol",source = "crypto.symbol")
    @Mapping(target = "currentPrice",source = "crypto.currentPrice")
    CryptoDTO convertEntityToDto(Crypto crypto);
}
