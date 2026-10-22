package com.acme.field.parts;

import java.util.Objects;

public class Part {

    private final String sku;
    private final String name;
    private final PartCategory category;
    private int quantity;
    private double unitPrice;

    public Part(String sku, String name, PartCategory category, int quantity, double unitPrice) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public PartCategory getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isInStock() {
        return quantity > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Part part = (Part) o;
        return quantity == part.quantity
                && Double.compare(part.unitPrice, unitPrice) == 0
                && Objects.equals(sku, part.sku)
                && category == part.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, category, quantity, unitPrice);
    }

    @Override
    public String toString() {
        return "Part{sku='" + sku + "', name='" + name + "', quantity=" + quantity + "}";
    }
}
