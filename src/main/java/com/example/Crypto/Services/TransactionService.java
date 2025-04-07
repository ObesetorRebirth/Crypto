package com.example.Crypto.Services;

import com.example.Crypto.DTOs.TransactionDTO;
import com.example.Crypto.Entities.Transaction;
import com.example.Crypto.Mappers.TransactionMapper;
import com.example.Crypto.Repositories.CryptoRepository;
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
    private final CryptoRepository cryptoRepository;
    private final UserCryptoService userCryptoService;

    public void buyCrypto(Long userId,Long cryptoId, Double quantity) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new EntityNotFoundException("User with this ID does not exist"));
        var userBalance = user.getBalance();
        var neededBalance = cryptoRepository.findCryptoCurrentPriceById(cryptoId)*quantity;
        if(DoubleComparator(neededBalance,userBalance)){
            throw new ArithmeticException("Not enough balance");
        }

        var newBalance = userBalance - neededBalance;
        user.setBalance(newBalance);

        userCryptoService.addToHoldings(userId,cryptoId,quantity);


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

    //utill
    private boolean DoubleComparator(double d1, double d2) {
        return Double.compare(d1,d2) >= 0;
    }
}
