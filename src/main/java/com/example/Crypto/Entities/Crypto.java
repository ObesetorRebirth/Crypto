package com.example.Crypto.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "crypto")
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crypto_name")
    private String cryptoName;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "current_price")
    private Float currentPrice;

    @OneToMany(mappedBy = "crypto",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transaction> transactions;

}
