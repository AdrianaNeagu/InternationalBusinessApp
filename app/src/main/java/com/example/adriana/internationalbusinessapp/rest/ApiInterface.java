package com.example.adriana.internationalbusinessapp.rest;

import com.example.adriana.internationalbusinessapp.model.ConversionRate;
import com.example.adriana.internationalbusinessapp.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("rates.json")
    Call<List<ConversionRate>> getAllConversions();

    @GET("transactions.json")
    Call<List<Transaction>> getAllTransactions();


}
