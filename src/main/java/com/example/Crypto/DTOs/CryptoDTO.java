package com.example.Crypto.DTOs;

import jakarta.validation.constraints.NotNull;

@NotNull
public record CryptoDTO (Long id,String cryptoName,String base64)
{
}
