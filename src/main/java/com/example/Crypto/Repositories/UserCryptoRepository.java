package com.example.Crypto.Repositories;

import com.example.Crypto.CompositeKeys.UserCryptoId;
import com.example.Crypto.Entities.IntermediaryEntities.UserCrypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCryptoRepository extends JpaRepository<UserCrypto, UserCryptoId> {

    Optional<UserCrypto> findByUserIdAndCryptoId(Long userId, Long cryptoId);
    List<UserCrypto> findByUserId(Long userId);
}