package com.acme.field.invoicing;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private final String number;
    private final String customerName;
    private final List<InvoiceLine> lines = new ArrayList<>();
    private boolean paid;
    private String note;

    public Invoice(String number, String customerName) {
        this.number = number;
        this.customerName = customerName;
    }

    public String getNumber() {
        return number;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void addLine(InvoiceLine line) {
        lines.add(line);
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getSubtotal() {
        double total = 0;
        for (InvoiceLine line : lines) {
            total += line.getTotal();
        }
        return total;
    }
}
