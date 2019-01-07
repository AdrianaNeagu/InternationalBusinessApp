package com.example.adriana.internationalbusinessapp.model;

import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("sku")
    private String sku;
    @SerializedName("amount")
    private String amount;
    @SerializedName("currency")
    private String currency;

    public Transaction(String sku, String amount, String currency) {
        this.sku = sku;
        this.amount = amount;
        this.currency = currency;
    }

    public String getSku() {
        return sku;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
