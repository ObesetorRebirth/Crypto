package com.example.Crypto.Services;

import com.example.Crypto.Entities.Transaction;
import com.example.Crypto.Mappers.TransactionMapper;
import com.example.Crypto.Repositories.TransactionRepository;
import com.example.Crypto.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.Crypto.Entities.User;
import com.example.Crypto.Entities.Crypto;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final UserRepository userRepository;

    public void buyCrypto(Long userId,Long cryptoId, Double quantity) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new EntityNotFoundException("User with this ID does not exist"));
        
    }

    public void sellCrypto(Long userId,Long cryptoId, Double quantity){

    }

    public List<Transaction> getUserTransactions(Long userId){
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        return transactions;
    }

    public void CalculateProfitOrLoss(Transaction transaction){

    }

    private boolean TransactionExists(Transaction transaction) {
        return true;
    }
}
