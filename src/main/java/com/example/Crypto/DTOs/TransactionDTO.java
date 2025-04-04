package com.example.Crypto.DTOs;

import java.time.LocalDateTime;

public record TransactionDTO (
        String transactionType,
        Long quantity,
        float priceAtTransaction,
        Long totalAmount,
        LocalDateTime transactionDate){
}
