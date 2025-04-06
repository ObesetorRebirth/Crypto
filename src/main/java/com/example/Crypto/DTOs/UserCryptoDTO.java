package com.example.Crypto.DTOs;

public record UserCryptoDTO(Long userId, Long cryptoId, String username, String cryptoName, Double quantity) {
}
