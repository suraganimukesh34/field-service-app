package com.acme.field.customers

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CustomerRepository private constructor() {

    private val cache = mutableMapOf<String, Customer>()
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    fun save(customer: Customer) {
        cache[customer.id] = customer
    }

    fun findCached(id: String): Customer? = cache[id]

    fun findById(id: String, callback: ResultCallback<Customer>) {
        executor.execute {
            val customer = cache[id]
            if (customer != null) {
                callback.onSuccess(customer)
            } else {
                callback.onError(IllegalStateException("No customer with id $id"))
            }
        }
    }

    fun all(): List<Customer> = cache.values.toMutableList()

    fun activeCount(): Int = cache.values.count { it.isActive }

    companion object {
        private var instance: CustomerRepository? = null

        @JvmStatic
        @Synchronized
        fun getInstance(): CustomerRepository =
            instance ?: CustomerRepository().also { instance = it }
    }
}
