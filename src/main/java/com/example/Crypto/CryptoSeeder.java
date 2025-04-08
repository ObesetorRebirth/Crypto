package com.example.Crypto;

import com.example.Crypto.Entities.Crypto;
import com.example.Crypto.Repositories.CryptoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;

@Component
public class CryptoSeeder implements CommandLineRunner {

    private final CryptoRepository cryptoRepository;

    public CryptoSeeder(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    @Override
    public void run(String... args) {
        Map<String, String> cryptos = Map.ofEntries(
                entry("BTC/USD", "Bitcoin"),
                entry("ETH/USD", "Ethereum"),
                entry("SOL/USD", "Solana"),
                entry("DOGE/USD", "Dogecoin"),
                entry("XRP/USD", "Ripple"),
                entry("LTC/USD", "Litecoin"),
                entry("LINK/USD", "Chainlink"),
                entry("ADA/USD", "Cardano"),
                entry("DOT/USD", "Polkadot"),
                entry("TRX/USD", "TRON"),
                entry("MATIC/USD", "Polygon"),
                entry("AVAX/USD", "Avalanche"),
                entry("UNI/USD", "Uniswap"),
                entry("ETC/USD", "Ethereum Classic"),
                entry("ATOM/USD", "Cosmos"),
                entry("XLM/USD", "Stellar"),
                entry("ALGO/USD", "Algorand"),
                entry("AAVE/USD", "Aave"),
                entry("EOS/USD", "EOS"),
                entry("NEAR/USD", "NEAR Protocol")
        );

        cryptos.forEach((symbol, name) -> {
            cryptoRepository.findBySymbol(symbol).orElseGet(() -> {
                Crypto crypto = new Crypto();
                crypto.setSymbol(symbol);
                crypto.setCryptoName(name);
                crypto.setCurrentPrice(0f);
                return cryptoRepository.save(crypto);
            });
        });

        System.out.println("✅ Crypto table seeded.");
    }
}