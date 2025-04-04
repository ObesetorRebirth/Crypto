package com.example.Crypto.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="transaction")
public class Transaction {

    public enum TransactionType {
        Buying(0),
        Selling(1);

        private final int type;

        TransactionType(int type)
        {
            this.type=type;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price_at_transaction")
    private Float priceAtTransaction;

    @Column(name="total_amount")
    private Long totalAmount;

    @Column(name="transaction_date")
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "crypto_id",nullable = false)
    private Crypto crypto;
}
