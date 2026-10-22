package com.acme.field.customers

fun interface ResultCallback<T> {

    fun onSuccess(result: T)

    fun onError(error: Throwable)
}
