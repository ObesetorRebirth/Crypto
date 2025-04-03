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
    private Long crypto_id;

    @Column(name = "crypto_name")
    private String cryptoName;

    @Column(name = "images")
    private String symbol64;

    @OneToMany(mappedBy = "crypto",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transaction> transactions;

}
