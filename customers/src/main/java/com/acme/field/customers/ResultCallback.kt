package com.acme.field.customers;

public interface ResultCallback<T> {

    void onSuccess(T result);

    void onError(Throwable error);
}
