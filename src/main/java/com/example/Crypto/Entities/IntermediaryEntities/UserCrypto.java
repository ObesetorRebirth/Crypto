package com.example.Crypto.Entities.IntermediaryEntities;

import com.example.Crypto.CompositeKeys.UserCryptoId;
import com.example.Crypto.Entities.Crypto;
import com.example.Crypto.Entities.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_crypto")
public class UserCrypto {

    @EmbeddedId
    private UserCryptoId id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "crypto_id", nullable = false)
    private Crypto crypto;

    @Column(name = "quantity", nullable = false)
    private Double quantity;
}
