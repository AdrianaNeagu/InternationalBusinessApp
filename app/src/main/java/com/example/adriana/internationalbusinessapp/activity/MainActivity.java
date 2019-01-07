package com.example.adriana.internationalbusinessapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adriana.internationalbusinessapp.R;
import com.example.adriana.internationalbusinessapp.adapter.TransactionAdapter;
import com.example.adriana.internationalbusinessapp.model.ConversionRate;
import com.example.adriana.internationalbusinessapp.model.Transaction;
import com.example.adriana.internationalbusinessapp.rest.ApiClient;
import com.example.adriana.internationalbusinessapp.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Transaction> transactionList = new ArrayList<>();
    private List<ConversionRate> conversionRateList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Transaction>> call = apiInterface.getAllTransactions();
        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                transactionList = response.body();
                currencyConverter();
                recyclerView.setAdapter(new TransactionAdapter(transactionList));
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        Call<List<ConversionRate>> callc = apiInterface.getAllConversions();
        callc.enqueue(new Callback<List<ConversionRate>>() {
            @Override
            public void onResponse(Call<List<ConversionRate>> call, Response<List<ConversionRate>> response) {
                conversionRateList = response.body();

            }

            @Override
            public void onFailure(Call<List<ConversionRate>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
    
    public Float getConversionRate(String currency) {
        float conversionRate = 0;
        for (ConversionRate rate : conversionRateList) {
            if((rate.getFrom() == currency) && (rate.getTo().contains("EUR"))) {
                conversionRate =  Float.parseFloat(rate.getRate());
            } else {

            }
        } return conversionRate;
    }

    public void currencyConverter() {
        for(Transaction transaction : transactionList) {
            transaction.setAmount(String.valueOf(Float.parseFloat(transaction.getAmount()) *
                    getConversionRate(transaction.getCurrency())));
            transaction.setCurrency("EUR");
        }
    }
}
