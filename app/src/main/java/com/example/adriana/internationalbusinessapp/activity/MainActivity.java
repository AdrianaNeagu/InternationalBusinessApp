package com.example.adriana.internationalbusinessapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adriana.internationalbusinessapp.R;
import com.example.adriana.internationalbusinessapp.adapter.TransactionAdapter;
import com.example.adriana.internationalbusinessapp.model.ConversionRate;
import com.example.adriana.internationalbusinessapp.model.Transaction;
import com.example.adriana.internationalbusinessapp.rest.ApiClient;
import com.example.adriana.internationalbusinessapp.rest.ApiInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
                recyclerView.setAdapter(new TransactionAdapter(transactionList, getApplicationContext()));
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


    public Double getConvertedAmount(Transaction transaction) {
        double conversionRate = 0;
        for (ConversionRate rate : conversionRateList) {
            if ((rate.getFrom() == transaction.getCurrency()) && (rate.getTo().contains("EUR"))) {
                conversionRate = Double.parseDouble(rate.getRate());
            } else {

                ArrayList<ConversionRate> fromList = new ArrayList<>();
                ArrayList<ConversionRate> toList = new ArrayList<>();

                for (ConversionRate fromRate : conversionRateList) {
                    if (fromRate.getFrom().equals(transaction.getCurrency())) {
                        fromList.add(fromRate);
                    }
                }

                for (ConversionRate toRate : conversionRateList) {
                    if (toRate.getTo().equals("EUR")) {
                        toList.add(toRate);
                    }
                }

                for (ConversionRate fromRate : fromList) {
                    for (ConversionRate toRate : toList) {
                        if (fromRate.getTo().equals(toRate.getFrom())) {
                            transaction.setAmount(String.valueOf(bankersRounding((Double.parseDouble(transaction.getAmount()) * Double.parseDouble(fromRate.getRate())) *
                                    Double.parseDouble(toRate.getRate()))));
                            break;
                        }
                    }
                }
            }
        }
        return conversionRate;

    }


    public void currencyConverter() {
        for (Transaction transaction : transactionList) {
            getConvertedAmount(transaction);
            transaction.setCurrency("EUR");
        }
    }

    public static double bankersRounding(double amount) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(amount));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_EVEN);
        return bigDecimal.doubleValue();
    }
}
