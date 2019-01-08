package com.example.adriana.internationalbusinessapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adriana.internationalbusinessapp.R;
import com.example.adriana.internationalbusinessapp.model.Transaction;

import java.util.List;

public class SkuTransactionsAdapter extends RecyclerView.Adapter<SkuTransactionsAdapter.ViewHolder> {

    private List<Transaction> transactionList;

    public SkuTransactionsAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public SkuTransactionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_list_item, viewGroup, false);
        return new SkuTransactionsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkuTransactionsAdapter.ViewHolder viewHolder, final int i) {
        final Transaction transaction = transactionList.get(i);
        viewHolder.skuTextView.setText(transaction.getSku());
        viewHolder.amountTextView.setText(transaction.getAmount());
        viewHolder.currencyTextView.setText(transaction.getCurrency());

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView skuTextView;
        TextView amountTextView;
        TextView currencyTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skuTextView = itemView.findViewById(R.id.sku_tv);
            amountTextView = itemView.findViewById(R.id.amount_tv);
            currencyTextView = itemView.findViewById(R.id.currency_tv);
        }
    }

}