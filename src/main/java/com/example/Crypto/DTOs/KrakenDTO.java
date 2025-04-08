package com.example.Crypto.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KrakenDTO {
    public String channel;
    public String type;
    public String symbol;
    public Object data;

    public String error;
    public Boolean success;
    public String method;
    public Integer req_id;

    public Float getPrice() {
        if (data instanceof List) {
            return null;
        } else if (data instanceof Data) {
            return ((Data) data).price != null ? Float.parseFloat(((Data) data).price) : null;
        }
        return null;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        public String price;
    }
}
