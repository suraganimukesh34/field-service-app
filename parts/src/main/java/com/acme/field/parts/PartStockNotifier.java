package com.acme.field.parts;

import java.util.ArrayList;
import java.util.List;

public class PartStockNotifier {

    public static final int LOW_STOCK_THRESHOLD = 5;

    private final List<StockListener> listeners = new ArrayList<>();

    public void addListener(StockListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(StockListener listener) {
        listeners.remove(listener);
    }

    public StockListener logging(final List<String> log) {
        return new StockListener() {
            @Override
            public void onLowStock(Part part, int threshold) {
                log.add(part.getSku() + " is below " + threshold);
            }
        };
    }

    public void check(Part part) {
        if (part.getQuantity() < LOW_STOCK_THRESHOLD) {
            for (StockListener listener : listeners) {
                listener.onLowStock(part, LOW_STOCK_THRESHOLD);
            }
        }
    }
}
