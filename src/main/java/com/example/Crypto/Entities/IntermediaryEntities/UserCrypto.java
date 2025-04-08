package com.example.Crypto.Entities.IntermediaryEntities;

import com.example.Crypto.CompositeKeys.UserCryptoId;
import com.example.Crypto.Entities.Crypto;
import com.example.Crypto.Entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_crypto")
public class UserCrypto {

    @EmbeddedId
    private UserCryptoId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @MapsId("cryptoId")
    @JoinColumn(name = "crypto_id", nullable = false)
    private Crypto crypto;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

}
