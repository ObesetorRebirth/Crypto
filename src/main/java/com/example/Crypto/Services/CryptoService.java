package com.example.Crypto.Services;

import com.example.Crypto.DTOs.CryptoDTO;
import com.example.Crypto.Entities.Crypto;
import com.example.Crypto.Mappers.CryptoMapper;
import com.example.Crypto.Repositories.CryptoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CryptoService {
    private final CryptoRepository cryptoRepository;
    private final CryptoMapper cryptoMapper;

    public void updateCryptoPrice(String symbol, Float newPrice) {
        Optional<Crypto> optionalCrypto = cryptoRepository.findBySymbol(symbol);
        if (optionalCrypto.isPresent()) {
            Crypto crypto = optionalCrypto.get();
            crypto.setCurrentPrice(newPrice);
            cryptoRepository.save(crypto);
        }
    }

    public Crypto getCryptoById(Long cryptoId){
        return getCrypto(cryptoId);
    }

    public void updateOrCreateCrypto(String symbol, Float newPrice) {
        Optional<Crypto> cryptoOpt = cryptoRepository.findBySymbol(symbol);
        Crypto crypto = cryptoOpt.orElse(createCrypto(symbol, newPrice));
        crypto.setCurrentPrice(newPrice);
        cryptoRepository.save(crypto);
    }

    public List<CryptoDTO> getTop20Cryptos(){
        List<Crypto> top20Cryptos = cryptoRepository.findTop20ByOrderByCurrentPriceDesc(PageRequest.of(0, 20));
        return top20Cryptos.stream().map(cryptoMapper::convertEntityToDto).toList();
    }

    //util
    private Crypto createCrypto (String symbol, Float price) {
        Crypto crypto = new Crypto();
        crypto.setCryptoName(symbol);
        crypto.setCurrentPrice(price);
        return crypto;
    }
    private Crypto getCrypto(Long cryptoId){
        return cryptoRepository.findById(cryptoId)
                .orElseThrow(() -> new EntityNotFoundException("Crypto ID:" + cryptoId));
    }
}
