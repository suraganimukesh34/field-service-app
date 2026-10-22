package com.acme.field.invoicing;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceActivity extends AppCompatActivity {

    public static final String EXTRA_CUSTOMER_ID = "customer_id";

    private InvoiceApi api;
    private InvoiceAdapter adapter;
    private TextView outstandingView;
    private String customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoices);

        customerId = getIntent().getStringExtra(EXTRA_CUSTOMER_ID);
        outstandingView = findViewById(R.id.outstanding);
        RecyclerView list = findViewById(R.id.invoice_list);
        Button refresh = findViewById(R.id.refresh);

        adapter = new InvoiceAdapter(new InvoiceAdapter.OnInvoiceClickListener() {
            @Override
            public void onInvoiceClick(Invoice invoice) {
                Toast.makeText(InvoiceActivity.this, invoice.getNumber(), Toast.LENGTH_SHORT).show();
            }
        });
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });
        load();
    }

    private void load() {
        api.list(customerId, false).enqueue(new Callback<List<Invoice>>() {
            @Override
            public void onResponse(Call<List<Invoice>> call, Response<List<Invoice>> response) {
                List<Invoice> body = response.body();
                if (body != null) {
                    adapter.setInvoices(body);
                    outstandingView.setText(String.format("%.2f", InvoiceCalculator.outstanding(body)));
                }
            }

            @Override
            public void onFailure(Call<List<Invoice>> call, Throwable t) {
                Toast.makeText(InvoiceActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
