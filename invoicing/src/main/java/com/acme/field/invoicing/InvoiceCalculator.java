package com.acme.field.invoicing;

import java.util.List;

public final class InvoiceCalculator {

    public static final double DEFAULT_TAX_RATE = 0.2;
    private static final double LARGE_ORDER_DISCOUNT = 0.05;
    private static final double LARGE_ORDER_LIMIT = 1000.0;

    private InvoiceCalculator() {
    }

    public static double discount(Invoice invoice) {
        double subtotal = invoice.getSubtotal();
        return subtotal > LARGE_ORDER_LIMIT ? subtotal * LARGE_ORDER_DISCOUNT : 0.0;
    }

    public static double tax(Invoice invoice, Double rate) {
        double effectiveRate = rate != null ? rate : DEFAULT_TAX_RATE;
        return (invoice.getSubtotal() - discount(invoice)) * effectiveRate;
    }

    public static double total(Invoice invoice, Double rate) {
        return invoice.getSubtotal() - discount(invoice) + tax(invoice, rate);
    }

    public static double outstanding(List<Invoice> invoices) {
        double sum = 0;
        for (Invoice invoice : invoices) {
            if (!invoice.isPaid()) {
                sum += total(invoice, null);
            }
        }
        return sum;
    }
}
