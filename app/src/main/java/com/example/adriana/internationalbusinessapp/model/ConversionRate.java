package com.example.adriana.internationalbusinessapp.model;

import com.google.gson.annotations.SerializedName;

public class ConversionRate {

    @SerializedName("from")
    private String from;
    @SerializedName("to")
    private String to;
    @SerializedName("rate")
    private String rate;

    public ConversionRate(String from, String to, String rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getRate() {
        return rate;
    }
}
