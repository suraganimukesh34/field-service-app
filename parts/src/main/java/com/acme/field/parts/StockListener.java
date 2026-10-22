package com.acme.field.parts;

public interface StockListener {

    void onLowStock(Part part, int threshold);
}
