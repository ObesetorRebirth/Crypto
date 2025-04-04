package com.example.Crypto.Services;

import com.example.Crypto.Entities.Crypto;
import com.example.Crypto.Repositories.CryptoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CryptoService {
    private final CryptoRepository cryptoRepository;

    public void updateCryptoPrice(String symbol, Float newPrice) {
        Optional<Crypto> cryptoOpt = cryptoRepository.findByCryptoName(symbol);
        cryptoOpt.ifPresent(crypto -> {
            crypto.setCurrentPrice(newPrice);
            cryptoRepository.save(crypto);
        });
    }

    public void updateOrCreateCrypto(String symbol, Float newPrice) {
        Optional<Crypto> cryptoOpt = cryptoRepository.findByCryptoName(symbol);
        Crypto crypto = cryptoOpt.orElse(createCrypto(symbol, newPrice));
        crypto.setCurrentPrice(newPrice);
        cryptoRepository.save(crypto);
    }

    private Crypto createCrypto (String symbol, Float price) {
        Crypto crypto = new Crypto();
        crypto.setCryptoName(symbol);
        crypto.setCurrentPrice(price);
        return crypto;
    }
}
