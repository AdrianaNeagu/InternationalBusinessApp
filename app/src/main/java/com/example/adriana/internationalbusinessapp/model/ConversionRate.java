package com.example.adriana.internationalbusinessapp.model;

import com.google.gson.annotations.SerializedName;

public class ConversionRate {

    @SerializedName("from")
    private String conversionFrom;
    @SerializedName("to")
    private String conversionTo;
    @SerializedName("rate")
    private String conversionRate;

    public ConversionRate(String conversionFrom, String conversionTo, String conversionRate) {
        this.conversionFrom = conversionFrom;
        this.conversionTo = conversionTo;
        this.conversionRate = conversionRate;
    }

    public String getConversionFrom() {
        return conversionFrom;
    }

    public String getConversionTo() {
        return conversionTo;
    }

    public String getConversionRate() {
        return conversionRate;
    }
}
