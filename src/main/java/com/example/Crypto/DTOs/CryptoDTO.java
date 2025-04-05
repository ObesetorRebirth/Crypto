package com.example.Crypto.DTOs;

import jakarta.validation.constraints.NotNull;

@NotNull
public record CryptoDTO (Long id,String cryptoName,String symbol, Float currentPrice)
{
}
