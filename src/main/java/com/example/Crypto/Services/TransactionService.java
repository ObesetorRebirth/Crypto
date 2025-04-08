package com.example.Crypto.Services;

import com.example.Crypto.DTOs.TransactionDTO;
import com.example.Crypto.Entities.IntermediaryEntities.UserCrypto;
import com.example.Crypto.Entities.Transaction;
import com.example.Crypto.Mappers.TransactionMapper;
import com.example.Crypto.Repositories.CryptoRepository;
import com.example.Crypto.Repositories.TransactionRepository;
import com.example.Crypto.Repositories.UserCryptoRepository;
import com.example.Crypto.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.Crypto.Entities.User;
import com.example.Crypto.Entities.Crypto;

import java.time.LocalDateTime;
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
    private final UserCryptoRepository userCryptoRepository;

    public void buyCrypto(Long userId,Long cryptoId, Double quantity) {

        User user = userRepository.findById(userId).
                orElseThrow(() -> new EntityNotFoundException("User with this ID does not exist"));

        Crypto crypto = cryptoRepository.findById(cryptoId)
                .orElseThrow(() -> new EntityNotFoundException("Crypto with this ID does not exist"));

        double userBalance = user.getBalance();
        double neededBalance = cryptoRepository.findCryptoCurrentPriceById(cryptoId)*quantity;

        if(doubleComparator(neededBalance,userBalance)){
            throw new ArithmeticException("Not enough balance");
        }

       double newBalance = userBalance - neededBalance;
        user.setBalance(newBalance);

        userCryptoService.addToHoldings(userId,cryptoId,quantity);

        createTransaction(userId,cryptoId,Transaction.TransactionType.Buying,quantity,cryptoRepository.findCryptoCurrentPriceById(cryptoId));
    }

    public void sellCrypto(Long userId, Long cryptoId, Double quantity){
        User user = userRepository.findById(userId).
                orElseThrow(() -> new EntityNotFoundException("User with this ID does not exist"));

        Crypto crypto = cryptoRepository.findById(cryptoId)
                .orElseThrow(() -> new EntityNotFoundException("Crypto with this ID does not exist"));

        UserCrypto holding = userCryptoRepository.findByUserIdAndCryptoId(userId,cryptoId)
                .orElseThrow(() -> new EntityNotFoundException("User with this ID does not own Crypto with this ID in their holdings"));

        if(!doubleComparator(holding.getQuantity(),quantity))
            throw new ArithmeticException("Not enough Crypto in balance");

        var price = crypto.getCurrentPrice();
        var addBalance = price*quantity;

        double newBalance = user.getBalance() + addBalance;
        user.setBalance(newBalance);

        double remainingQuantity = holding.getQuantity() - quantity;
        if (remainingQuantity > 0) {
            holding.setQuantity(remainingQuantity);
            userCryptoRepository.save(holding);
        } else {
            userCryptoRepository.delete(holding);
        }

        createTransaction(userId,cryptoId,Transaction.TransactionType.Selling,quantity, Double.valueOf(price));
    }

    public List<Transaction> getUserTransactionsByType(Long userId,Transaction.TransactionType transactionType){
        List<Transaction> Transactions = transactionRepository.findAllByUserIdAndTransactionType(userId, transactionType);
        return Transactions;
    }

    public List<Transaction> getUserTransactions(Long userId){
        List<Transaction> transactions =transactionRepository.findAllTransactionsByUserId(userId);
        return transactions;
    }

    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findTransactionById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction ID:" + transactionId));
    }

    public Double calculateProfitOrLoss(Transaction transaction){
        if (transaction.getTransactionType() != Transaction.TransactionType.Selling) {
            throw new IllegalArgumentException("Profit or loss can only be calculated for SELLING transactions.");
        }

        Long userId = transaction.getUser().getId();
        Long cryptoId = transaction.getCrypto().getId();
        double sellPrice = transaction.getPriceAtTransaction();
        double quantitySold = transaction.getQuantity();

        List<Transaction> pastBuys = transactionRepository
                .findAllByUserIdAndCryptoIdAndTransactionType(userId, cryptoId, Transaction.TransactionType.Buying);

        if (pastBuys.isEmpty()) {
            throw new IllegalStateException("No previous BUYING transactions found to calculate profit/loss.");
        }

        double totalBuyCost = 0;
        double totalBuyQuantity = 0;

        for (Transaction buy : pastBuys) {
            totalBuyCost += buy.getPriceAtTransaction() * buy.getQuantity();
            totalBuyQuantity += buy.getQuantity();
        }

        if (totalBuyQuantity == 0) {
            throw new ArithmeticException("Total bought quantity is zero, cannot calculate average buy price.");
        }

        double averageBuyPrice = totalBuyCost / totalBuyQuantity;

        double profitOrLoss = (sellPrice - averageBuyPrice) * quantitySold;
        return profitOrLoss;
    }

    private boolean transactionExists(Long transactionId) {
        return transactionId != null && transactionRepository.existsById(transactionId);
    }

    //utill
    private boolean doubleComparator(double d1, double d2) {
        return Double.compare(d1,d2) >= 0;
    }

    private Transaction saveTransactionToDatabase(TransactionDTO transactionDTO, Long transactionId){
        var transaction = transactionMapper.convertDtoToEntity(transactionDTO,transactionId);
        return transactionRepository.save(transaction);
    }

    private void createTransaction(Long userId, Long cryptoId, Transaction.TransactionType transactionType, Double quantity, Double priceAtTransaction) {
        double totalAmount = quantity * priceAtTransaction;

        TransactionDTO transactionDTO = new TransactionDTO(
                transactionType,
                quantity,
                priceAtTransaction,
                (long) totalAmount,
                LocalDateTime.now()
        );

        Transaction transaction = transactionMapper.convertDtoToEntity(transactionDTO, null);

        transaction.setUser(userRepository.getReferenceById(userId));
        transaction.setCrypto(cryptoRepository.getReferenceById(cryptoId));

        transactionRepository.saveAndFlush(transaction);
    }
}
