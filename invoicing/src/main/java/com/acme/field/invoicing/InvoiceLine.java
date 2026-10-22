package com.acme.field.invoicing;

import java.util.Objects;

public class InvoiceLine {

    private final String description;
    private final int quantity;
    private final double unitPrice;

    public InvoiceLine(String description, int quantity, double unitPrice) {
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotal() {
        return quantity * unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvoiceLine other = (InvoiceLine) o;
        return quantity == other.quantity
                && Double.compare(other.unitPrice, unitPrice) == 0
                && Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, quantity, unitPrice);
    }
}
