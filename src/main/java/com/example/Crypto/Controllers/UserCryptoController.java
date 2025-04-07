package com.example.Crypto.Controllers;

import com.example.Crypto.DTOs.UserCryptoDTO;
import com.example.Crypto.Entities.IntermediaryEntities.UserCrypto;
import com.example.Crypto.Services.UserCryptoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/holding")
public class UserCryptoController {
    private final UserCryptoService userCryptoService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<UserCryptoDTO>> getAllHoldingsForUser(@PathVariable Long userId) {
        var holdings = userCryptoService.getHoldingsByUser(userId);
        return new ResponseEntity<>(holdings, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/{cryptoId}")
    public ResponseEntity<UserCryptoDTO> getSpecificHoldingForUser(@PathVariable Long userId, @PathVariable Long cryptoId) {
        var holding = userCryptoService.getHolding(userId, cryptoId);
        return new ResponseEntity<>(holding, HttpStatus.OK);
    }
}
