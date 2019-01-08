package com.example.adriana.internationalbusinessapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Transaction implements Serializable {

    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
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

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
