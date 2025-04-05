package com.example.Crypto.Repositories;

import com.example.Crypto.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Page<User> findAll(Pageable pageable);
    Optional<User> findUserByUsername(String username);
    Long findBalanceByUserId(Long userId);
    void setBalance(Long userId,float newBalance);
}
