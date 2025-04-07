package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.TransactionDTO;
import com.example.Crypto.Entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {
    @Mapping(target = "transactionType",source = "transactionDTO.transactionType")
    @Mapping(target = "id",source = "transactionDTO.id")
    @Mapping(target = "quantity",source = "transactionDTO.quantity")
    @Mapping(target = "priceAtTransaction",source = "transactionDTO.priceAtTransaction")
    @Mapping(target = "totalAmount",source = "transactionDTO.totalAmount")
    @Mapping(target = "transactionDate",source = "transactionDTO.transactionDate")
    Transaction convertDtoToEntity(TransactionDTO transactionDTO,Long transactionId);

    @Mapping(target = "transactionType", source = "transaction.transactionType")
    @Mapping(target = "quantity", source = "transaction.quantity")
    @Mapping(target = "id",source = "transaction.id")
    @Mapping(target = "priceAtTransaction", source = "transaction.priceAtTransaction")
    @Mapping(target = "totalAmount", source = "transaction.totalAmount")
    @Mapping(target = "transactionDate", source = "transaction.transactionDate")
    TransactionDTO convertEntityToDto(Transaction transaction);
}
