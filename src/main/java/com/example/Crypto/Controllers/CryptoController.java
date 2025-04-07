package com.example.Crypto.Controllers;

import com.example.Crypto.DTOs.CryptoDTO;
import com.example.Crypto.Entities.Crypto;
import com.example.Crypto.Services.CryptoService;
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
@RequestMapping(value = "/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    @GetMapping(value = "/top20")
    public ResponseEntity<List<CryptoDTO>> getTop20Cryptos() {
        var top20Cryptos = cryptoService.getTop20Cryptos();
        return new ResponseEntity<>(top20Cryptos, HttpStatus.OK);
    }

    @GetMapping(value = "/{cryptoId}")
    public ResponseEntity<Crypto> getCrypto(@PathVariable Long cryptoId) {
        var crypto = cryptoService.getCryptoById(cryptoId);
        return new ResponseEntity<>(crypto, HttpStatus.OK);
    }
}
