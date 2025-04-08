package com.example.Crypto.DTOs;

import jakarta.validation.constraints.NotNull;

@NotNull
public record UserDTO (String username){}
