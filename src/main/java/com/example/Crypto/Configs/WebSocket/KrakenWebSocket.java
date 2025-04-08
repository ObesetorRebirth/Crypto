package com.example.Crypto.Configs.WebSocket;

import com.example.Crypto.Services.CryptoService;
import com.fasterxml.jackson.databind.JsonNode;
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
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void connect() {
        try {
            client = new WebSocketClient(new URI("wss://ws.kraken.com/v2")) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("WebSocket Opened");
                    subscribe();
                }

                @Override
                public void onMessage(String message) {
                    try {
                        // Log the raw message
                        System.out.println("Received message: " + message);

                        JsonNode json = objectMapper.readTree(message);

                        // Only handle messages with type "update" and channel "ticker"
                        if (!json.has("type") || !json.has("channel") ||
                                !"update".equals(json.get("type").asText()) ||
                                !"ticker".equals(json.get("channel").asText())) {
                            return;
                        }

                        // Make sure "data" is an array
                        JsonNode dataArray = json.get("data");
                        if (dataArray != null && dataArray.isArray()) {
                            for (JsonNode ticker : dataArray) {
                                String symbol = ticker.has("symbol") ? ticker.get("symbol").asText() : null;
                                Float lastPrice = ticker.has("last") ? (float) ticker.get("last").asDouble() : null;

                                if (symbol != null && lastPrice != null) {
                                    System.out.println("Updating price for " + symbol + ": " + lastPrice);
                                    cryptoService.updateCryptoPrice(symbol, lastPrice);
                                }
                            }
                        }

                    } catch (Exception e) {
                        System.err.println("Failed to process message: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("WebSocket Closed: " + reason);
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
            String subscriptionMessage = """
            {
              "method": "subscribe",
              "params": {
                "channel": "ticker",
                "symbol": [
                  "BTC/USD", "ETH/USD", "SOL/USD", "ADA/USD", "XRP/USD", "DOGE/USD", "DOT/USD", "LTC/USD", "AVAX/USD", "BNB/USD",
                  "LINK/USD", "MATIC/USD", "ATOM/USD", "TRX/USD", "ETC/USD", "UNI/USD", "BCH/USD", "XLM/USD", "NEAR/USD", "FIL/USD"
                ]
              },
              "req_id": 123
            }
            """;

            System.out.println("Sent subscription message: " + subscriptionMessage);
            client.send(subscriptionMessage);
        }
    }

    public void disconnect() {
        if (client != null && !client.isClosed()) {
            client.close();
        }
    }
}
