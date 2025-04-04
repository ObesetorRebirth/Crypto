package com.example.Crypto.DTOs;

public class KrakenDTO {
    public String channel;
    public String type;
    public String symbol;
    public Data data;

    public static class Data {
        public String price;
    }
}
