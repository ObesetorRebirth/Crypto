package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.TransactionDTO;
import com.example.Crypto.Entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {
    @Mapping(target = "transactionType",source = "transactionDTO.transactionType")
    @Mapping(target = "quantity",source = "transactionDTO.quantity")
    @Mapping(target = "priceAtTransaction",source = "transactionDTO.priceAtTransaction")
    @Mapping(target = "totalAmount",source = "transactionDTO.totalAmount")
    @Mapping(target = "transactionDate",source = "transactionDTO.transactionDate")
    Transaction convertDtoToEntity(TransactionDTO transactionDTO);

    @Mapping(target = "transactionType", source = "transactionType")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "priceAtTransaction", source = "priceAtTransaction")
    @Mapping(target = "totalAmount", source = "totalAmount")
    @Mapping(target = "transactionDate", source = "transactionDate")
    TransactionDTO convertEntityToDto(Transaction transaction);
}
