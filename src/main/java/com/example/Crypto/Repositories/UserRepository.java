package com.example.Crypto.Repositories;

import com.example.Crypto.Entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Page<User> findAll(Pageable pageable);
    Optional<User> findUserByUsername(String username);
    @Query("SELECT u.balance FROM User u WHERE u.id = :userId")
    Double findBalanceById(Long userId);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.balance = :newBalance WHERE u.id = :userId")
    void updateBalance(Long userId, Double newBalance);

}
