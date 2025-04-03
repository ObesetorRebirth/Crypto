package com.example.Crypto.Entities;

import com.example.Crypto.Entities.IntermediaryEntities.UserCrypto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "account_balance")
    private float accountBalance;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCrypto> holdings;
}
