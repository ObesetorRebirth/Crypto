package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.CryptoDTO;
import com.example.Crypto.Entities.Crypto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-08T09:36:21+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class CryptoMapperImpl implements CryptoMapper {

    @Override
    public Crypto convertDtoToEntity(CryptoDTO cryptoDTO, Long cryptoId) {
        if ( cryptoDTO == null && cryptoId == null ) {
            return null;
        }

        Crypto crypto = new Crypto();

        if ( cryptoDTO != null ) {
            crypto.setCryptoName( cryptoDTO.cryptoName() );
            crypto.setSymbol( cryptoDTO.symbol() );
            crypto.setCurrentPrice( cryptoDTO.currentPrice() );
        }
        crypto.setId( cryptoId );

        return crypto;
    }

    @Override
    public CryptoDTO convertEntityToDto(Crypto crypto) {
        if ( crypto == null ) {
            return null;
        }

        String cryptoName = null;
        String symbol = null;
        Float currentPrice = null;
        Long id = null;

        cryptoName = crypto.getCryptoName();
        symbol = crypto.getSymbol();
        currentPrice = crypto.getCurrentPrice();
        id = crypto.getId();

        CryptoDTO cryptoDTO = new CryptoDTO( id, cryptoName, symbol, currentPrice );

        return cryptoDTO;
    }
}
