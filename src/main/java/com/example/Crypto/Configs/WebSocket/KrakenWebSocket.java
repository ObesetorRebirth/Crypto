package com.example.Crypto.Configs.WebSocket;

import com.example.Crypto.DTOs.KrakenDTO;
import com.example.Crypto.Services.CryptoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class KrakenWebSocket extends WebSocketClient {

    private final CryptoService cryptoService;

    @Autowired
    public KrakenWebSocket(CryptoService cryptoService) throws URISyntaxException {
        super(new URI("wss://ws.kraken.com/v2"));
        this.cryptoService = cryptoService;
    }

    @PostConstruct
    public void init() {
        this.connect(); // Now we connect after cryptoService is injected
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("WebSocket Opened");

        // Subscribe to the ticker feed for BTC/USDT and ETH/USDT
        String subscriptionMessage = """
        {
          "method": "subscribe",
          "params": {
            "channel": "ticker",
            "symbols": ["XBT/USDT", "ETH/USDT"]
          },
          "req_id": 1
        }
        """;

        this.send(subscriptionMessage);
    }

    @Override
    public void onMessage(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            KrakenDTO ticker = mapper.readValue(message, KrakenDTO.class);

            if ("ticker".equals(ticker.channel) && "update".equals(ticker.type)) {
                String symbol = ticker.symbol;
                Float price = Float.parseFloat(ticker.data.price);

                cryptoService.updateCryptoPrice(symbol, price);
            }

        } catch (Exception e) {
            System.err.println("Failed to parse message: " + e.getMessage());
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("WebSocket Closed: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket Error: " + ex.getMessage());
    }
}
