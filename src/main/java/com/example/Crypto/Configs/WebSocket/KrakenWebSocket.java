package com.example.Crypto.Configs.WebSocket;

import com.example.Crypto.DTOs.KrakenDTO;
import com.example.Crypto.Services.CryptoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class KrakenWebSocket {

    private final CryptoService cryptoService;
    private final ObjectMapper objectMapper;
    private WebSocketClient client;

    @Autowired
    public KrakenWebSocket(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
        this.objectMapper = new ObjectMapper();
        // Configure ObjectMapper to be more lenient with unknown properties and types
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        this.objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    public void connect() {
        try {
            // Create a new client instance each time we connect
            client = new WebSocketClient(new URI("wss://ws.kraken.com/v2")) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("WebSocket Opened");
                    subscribe();
                }

                @Override
                public void onMessage(String message) {
                    try {
                        System.out.println("Received message: " + message);

                        // Check if it's a status update or error message
                        if (message.contains("\"status\"") || message.contains("\"error\"")) {
                            // Just log it and don't try to process as ticker data
                            return;
                        }

                        KrakenDTO ticker = objectMapper.readValue(message, KrakenDTO.class);

                        // Process only if it's a ticker update with price data
                        if ("ticker".equals(ticker.channel) && "update".equals(ticker.type) &&
                                ticker.symbol != null && ticker.getPrice() != null) {

                            String symbol = ticker.symbol;
                            Float price = ticker.getPrice();

                            System.out.println("Updating price for " + symbol + ": " + price);
                            cryptoService.updateCryptoPrice(symbol, price);
                        }
                    } catch (Exception e) {
                        System.err.println("Failed to parse message: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("WebSocket Closed: " + reason);

                    // Attempt to reconnect after a delay
                    if (remote) {
                        try {
                            Thread.sleep(5000);
                            connect();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }

                @Override
                public void onError(Exception ex) {
                    System.err.println("WebSocket Error: " + ex.getMessage());
                }
            };

            client.connect();
        } catch (URISyntaxException e) {
            System.err.println("Invalid WebSocket URI: " + e.getMessage());
        }
    }

    private void subscribe() {
        if (client != null && client.isOpen()) {
            // Subscribe to top 20 cryptocurrencies
            String subscriptionMessage = """
            {
              "method": "subscribe",
              "params": {
                "channel": "ticker",
                "symbol": [
                    "XBT/USDT",  // Bitcoin
                    "ETH/USDT",  // Ethereum
                    "SOL/USDT",  // Solana
                    "XRP/USDT",  // Ripple
                    "ADA/USDT",  // Cardano
                    "DOT/USDT",  // Polkadot
                    "DOGE/USDT", // Dogecoin
                    "AVAX/USDT", // Avalanche
                    "LINK/USDT", // Chainlink
                    "LTC/USDT",  // Litecoin
                    "UNI/USDT",  // Uniswap
                    "ATOM/USDT", // Cosmos
                    "MATIC/USDT", // Polygon
                    "ALGO/USDT", // Algorand
                    "FIL/USDT",  // Filecoin
                    "TRX/USDT",  // TRON
                    "XTZ/USDT",  // Tezos
                    "AAVE/USDT", // Aave
                    "EOS/USDT",  // EOS
                    "BCH/USDT"   // Bitcoin Cash
                ]
              },
              "req_id": 1
            }
            """;

            client.send(subscriptionMessage);
        }
    }

    public void disconnect() {
        if (client != null && !client.isClosed()) {
            client.close();
        }
    }
}