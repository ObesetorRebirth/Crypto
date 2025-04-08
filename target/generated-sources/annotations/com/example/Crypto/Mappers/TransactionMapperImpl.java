package com.example.Crypto.Mappers;

import com.example.Crypto.DTOs.TransactionDTO;
import com.example.Crypto.Entities.Transaction;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-08T09:36:21+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction convertDtoToEntity(TransactionDTO transactionDTO, Long transactionId) {
        if ( transactionDTO == null && transactionId == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        if ( transactionDTO != null ) {
            transaction.setTransactionType( transactionDTO.transactionType() );
            if ( transactionDTO.quantity() != null ) {
                transaction.setQuantity( transactionDTO.quantity().longValue() );
            }
            transaction.setPriceAtTransaction( transactionDTO.priceAtTransaction() );
            transaction.setTotalAmount( transactionDTO.totalAmount() );
            transaction.setTransactionDate( transactionDTO.transactionDate() );
        }

        return transaction;
    }

    @Override
    public TransactionDTO convertEntityToDto(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        Transaction.TransactionType transactionType = null;
        Double quantity = null;
        Double priceAtTransaction = null;
        Long totalAmount = null;
        LocalDateTime transactionDate = null;

        transactionType = transaction.getTransactionType();
        if ( transaction.getQuantity() != null ) {
            quantity = transaction.getQuantity().doubleValue();
        }
        priceAtTransaction = transaction.getPriceAtTransaction();
        totalAmount = transaction.getTotalAmount();
        transactionDate = transaction.getTransactionDate();

        TransactionDTO transactionDTO = new TransactionDTO( transactionType, quantity, priceAtTransaction, totalAmount, transactionDate );

        return transactionDTO;
    }
}
