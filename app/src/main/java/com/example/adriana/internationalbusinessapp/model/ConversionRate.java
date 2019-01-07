package com.example.adriana.internationalbusinessapp.model;

import com.google.gson.annotations.SerializedName;

public class ConversionRate {

    @SerializedName("from")
    private String conversionFrom;
    @SerializedName("to")
    private String conversionTo;
    @SerializedName("rate")
    private String conversionRate;


}
