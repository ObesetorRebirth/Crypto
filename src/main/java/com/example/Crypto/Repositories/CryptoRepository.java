package com.example.Crypto.Repositories;

import com.example.Crypto.Entities.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {
    Optional<Crypto> findByCryptoName(String symbol);

}
