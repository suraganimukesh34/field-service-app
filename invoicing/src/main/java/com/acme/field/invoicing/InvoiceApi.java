package com.acme.field.invoicing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InvoiceApi {

    @GET("invoices")
    Call<List<Invoice>> list(@Query("customer") String customerId, @Query("unpaid") boolean unpaidOnly);

    @GET("invoices/{number}")
    Call<Invoice> get(@Path("number") String number);

    @POST("invoices")
    Call<Invoice> create(@Body Invoice invoice);
}
