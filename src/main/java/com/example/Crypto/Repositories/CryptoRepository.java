package com.example.Crypto.Repositories;

import com.example.Crypto.Entities.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {
}
