package com.example.Crypto.DTOs;

import com.example.Crypto.Entities.Transaction;

import java.time.LocalDateTime;

public record TransactionDTO (
        Transaction.TransactionType transactionType,
        Double quantity,
        Double priceAtTransaction,
        Long totalAmount,
        LocalDateTime transactionDate){
}
