package com.acme.field.customers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerRepository {

    private static CustomerRepository instance;

    private final Map<String, Customer> cache = new HashMap<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private CustomerRepository() {
    }

    public static synchronized CustomerRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRepository();
        }
        return instance;
    }

    public void save(Customer customer) {
        cache.put(customer.getId(), customer);
    }

    public Customer findCached(String id) {
        return cache.get(id);
    }

    public void findById(final String id, final ResultCallback<Customer> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Customer customer = cache.get(id);
                if (customer != null) {
                    callback.onSuccess(customer);
                } else {
                    callback.onError(new IllegalStateException("No customer with id " + id));
                }
            }
        });
    }

    public List<Customer> all() {
        return new ArrayList<>(cache.values());
    }

    public int activeCount() {
        int count = 0;
        for (Customer customer : cache.values()) {
            if (customer.isActive()) {
                count++;
            }
        }
        return count;
    }
}
