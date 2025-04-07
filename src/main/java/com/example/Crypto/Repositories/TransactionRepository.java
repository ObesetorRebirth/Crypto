package com.example.Crypto.Repositories;

import com.example.Crypto.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
List<Transaction> findAllTransactionsByUserId(Long userId);
Optional<Transaction> findTransactionById (Long transactionId);
List<Transaction> findAllByUserIdAndCryptoIdAndTransactionType(Long userId, Long cryptoId, Transaction.TransactionType transactionType);
}
