package com.acme.field.invoicing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    public interface OnInvoiceClickListener {
        void onInvoiceClick(Invoice invoice);
    }

    private final List<Invoice> invoices = new ArrayList<>();
    private final OnInvoiceClickListener listener;

    public InvoiceAdapter(OnInvoiceClickListener listener) {
        this.listener = listener;
    }

    public void setInvoices(List<Invoice> newInvoices) {
        invoices.clear();
        invoices.addAll(newInvoices);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Invoice invoice = invoices.get(position);
        holder.title.setText(invoice.getNumber() + " - " + invoice.getCustomerName());
        holder.amount.setText(String.format("%.2f", InvoiceCalculator.total(invoice, null)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onInvoiceClick(invoice);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView amount;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.invoice_title);
            amount = itemView.findViewById(R.id.invoice_amount);
        }
    }
}
