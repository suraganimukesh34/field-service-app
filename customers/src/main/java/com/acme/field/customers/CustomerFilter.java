package com.acme.field.customers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class CustomerFilter {

    private CustomerFilter() {
    }

    public static List<Customer> search(List<Customer> customers, String query) {
        List<Customer> result = new ArrayList<>();
        String needle = query == null ? "" : query.trim().toLowerCase();
        for (Customer customer : customers) {
            if (needle.isEmpty()
                    || customer.getName().toLowerCase().contains(needle)
                    || (customer.getEmail() != null && customer.getEmail().toLowerCase().contains(needle))) {
                result.add(customer);
            }
        }
        return result;
    }

    public static List<Customer> activeOnly(List<Customer> customers) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.isActive()) {
                result.add(customer);
            }
        }
        return result;
    }

    public static void sortByName(List<Customer> customers) {
        Collections.sort(customers, new Comparator<Customer>() {
            @Override
            public int compare(Customer a, Customer b) {
                return a.getName().compareToIgnoreCase(b.getName());
            }
        });
    }
}
