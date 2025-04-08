package com.example.Crypto.Repositories;

import com.example.Crypto.Entities.Crypto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {
    Optional<Crypto> findBySymbol(String symbol);
    Page<Crypto> findAll(Pageable pageable);
    @Query("SELECT c FROM Crypto c ORDER BY c.currentPrice DESC")
    List<Crypto> findTop20ByOrderByCurrentPriceDesc(Pageable pageable);
    @Query("SELECT c.currentPrice FROM Crypto c WHERE c.id = :cryptoId")
    Double findCryptoCurrentPriceById(@Param("cryptoId") Long cryptoId);
}
