package com.example.Crypto.Entities;

import com.example.Crypto.Entities.IntermediaryEntities.UserCrypto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
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

    @Column(name = "symbol",unique = true)
    private String symbol;

    @Column(name = "current_price")
    private Float currentPrice;

    @OneToMany(mappedBy = "crypto",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "crypto",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCrypto> holdings;

    public String getCryptoName() {
        return cryptoName;
    }
}
