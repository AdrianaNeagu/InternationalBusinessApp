package com.example.adriana.internationalbusinessapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adriana.internationalbusinessapp.R;
import com.example.adriana.internationalbusinessapp.activity.MainActivity;
import com.example.adriana.internationalbusinessapp.activity.SkuTransactionsActivity;
import com.example.adriana.internationalbusinessapp.model.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transaction> transactionList;
    private Context context;

    public TransactionAdapter(List<Transaction> transactionList, Context context) {
        this.transactionList = transactionList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_list_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Transaction transaction = transactionList.get(i);
        viewHolder.skuTextView.setText(transaction.getSku());
        viewHolder.amountTextView.setText(transaction.getAmount());
        viewHolder.currencyTextView.setText(transaction.getCurrency());

        viewHolder.transactionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SkuTransactionsActivity.class);
                intent.putExtra("sku_number", transaction.getSku());
                ArrayList<Transaction> skuList = new ArrayList<>();
                for(Transaction transaction1 : transactionList) {
                    if(transaction1.getSku().equals(transaction.getSku())) {
                        skuList.add(transaction1);
                    }
                }
                intent.putExtra("transactions", (Serializable) skuList);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView skuTextView;
        TextView amountTextView;
        TextView currencyTextView;
        LinearLayout transactionLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skuTextView = itemView.findViewById(R.id.sku_tv);
            amountTextView = itemView.findViewById(R.id.amount_tv);
            currencyTextView = itemView.findViewById(R.id.currency_tv);
            transactionLayout = itemView.findViewById(R.id.transaction_layout);
        }
    }

}