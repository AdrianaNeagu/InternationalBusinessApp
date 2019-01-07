package com.example.adriana.internationalbusinessapp.model;

import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("sku")
    private String sku;
    @SerializedName("amount")
    private String amount;
    @SerializedName("currency")
    private String currency;
}
