package com.example.Crypto.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KrakenDTO {
    public String channel;
    public String type;
    public String symbol;
    public Object data; // Can be either List or Object based on message type

    // For error messages
    public String error;
    public Boolean success;
    public String method;
    public Integer req_id;

    // Helper method to handle different data types
    public Float getPrice() {
        if (data instanceof List) {
            // Handle array data
            return null; // Status updates don't have price data
        } else if (data instanceof Data) {
            // Handle object data for ticker updates
            return ((Data) data).price != null ? Float.parseFloat(((Data) data).price) : null;
        }
        return null;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        public String price;
    }
}
