package com.example.adriana.internationalbusinessapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adriana.internationalbusinessapp.R;
import com.example.adriana.internationalbusinessapp.adapter.SkuTransactionsAdapter;
import com.example.adriana.internationalbusinessapp.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class SkuTransactionsActivity extends AppCompatActivity {

    private ArrayList<Transaction> transactionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private String skuNumber = "";
    private TextView totalAmountTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sku_transactions);
        totalAmountTv = findViewById(R.id.total_amount_sku);
        recyclerView = findViewById(R.id.recycler_view_sku);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getIncomingIntent();
        recyclerView.setAdapter(new SkuTransactionsAdapter(transactionList));

        totalAmountTv.setText(getTotalAmount(transactionList).toString());
    }

    public void getIncomingIntent() {
        if(getIntent().hasExtra("sku_number") && getIntent().hasExtra("transactions")) {
            skuNumber = getIntent().getStringExtra("sku_number");
            transactionList = (ArrayList<Transaction>) getIntent().getExtras().getSerializable("transactions");
            Toast.makeText(getApplicationContext(), skuNumber, Toast.LENGTH_SHORT).show();
        }
    }


    public Double getTotalAmount(ArrayList<Transaction> list) {
        double totalAmount = 0.0;
        for(Transaction transaction : list) {
            totalAmount = totalAmount + Double.parseDouble(transaction.getAmount());
        } return MainActivity.bankersRounding(totalAmount);
    }
}
