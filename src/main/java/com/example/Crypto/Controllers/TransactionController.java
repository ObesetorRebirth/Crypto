package com.example.Crypto.Controllers;

import com.example.Crypto.Entities.Transaction;
import com.example.Crypto.Services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping(value = "/{userId}/{transactionType}")
    public ResponseEntity<List<Transaction>> getUserTransactionsByType(@PathVariable Long userId, @PathVariable Transaction.TransactionType transactionType) {
        var transactions = transactionService.getUserTransactionsByType(userId, transactionType);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/buy/{cryptoId}")
    public ResponseEntity<?> buyCrypto(
            @PathVariable Long userId,
            @PathVariable Long cryptoId,
            @RequestParam Double quantity
    ) {
        try {
            transactionService.buyCrypto(userId, cryptoId, quantity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/{userId}/sell/{cryptoId}")
    public ResponseEntity<?> sellCrypto(
            @PathVariable Long userId,
            @PathVariable Long cryptoId,
            @RequestParam Double quantity
    ) {
        try {
            transactionService.sellCrypto(userId, cryptoId, quantity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{transactionId}/profitOrLoss")
    public ResponseEntity<Double> checkProfitOrLoss(@PathVariable Long transactionId) {
        var transaction = transactionService.getTransactionById(transactionId);
        var profitOrLoss = transactionService.calculateProfitOrLoss(transaction);
        return new ResponseEntity<>(profitOrLoss, HttpStatus.OK);
    }
}