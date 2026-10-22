package com.acme.field.customers;

public class CustomerFormatter {

    public static final String UNKNOWN = "Unknown customer";
    private static final int MAX_NAME_LENGTH = 28;

    private CustomerFormatter() {
    }

    public static String displayName(Customer customer) {
        if (customer == null || customer.getName() == null) {
            return UNKNOWN;
        }
        String name = customer.getName().trim();
        return name.length() > MAX_NAME_LENGTH ? name.substring(0, MAX_NAME_LENGTH - 1) + "…" : name;
    }

    public static String contactLine(Customer customer) {
        StringBuilder line = new StringBuilder();
        line.append(customer.getEmail());
        if (customer.getPhone() != null) {
            line.append(" | ").append(customer.getPhone());
        }
        return line.toString();
    }

    public static String statusLabel(Customer customer) {
        return customer.isActive() ? "Active" : "Inactive";
    }
}
